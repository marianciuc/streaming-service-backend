package zut.wi.streamingservice.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.dto.StripeChargeDto;
import zut.wi.streamingservice.dto.StripeTokenDto;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class StripeService {

    @Value("$api.stripe.key")
    private String stripeApiKey;


    /**
     * The init function is called by the Spring Framework when it starts up.
     * It sets the Stripe API key to be used for all requests made from this application.
     *
     * @return Nothing
     * @docauthor Trelent
     */
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }


    /**
     * The createCardToken function is used to create a token for the card information provided.
     * This function takes in a StripeTokenDto object, which contains all of the necessary card information.
     * The function then creates a Map&lt;String, Object&gt; object called &quot;card&quot; and adds all of the necessary card info to it.
     * It then creates another Map&lt;String, Object&gt; object called &quot;params&quot; and adds the &quot;card&quot; map as an entry with key value pair (&quot;card&quot;, card).
     * Then it uses this params map to create a Token using Stripe's API (Token token = Token.create(params)).
     *
     * @param StripeTokenDto model Pass the card details to stripe
     *
     * @return A stripetokendto object
     *
     * @docauthor Trelent
     */
    public StripeTokenDto createCardToken(StripeTokenDto model) {
        try {
            Map<String, Object> card = new HashMap<>();
            card.put("number", Integer.parseInt(model.getCardNumber()));
            card.put("exp_month", Integer.parseInt(model.getExpMonth()));
            card.put("exp_year", model.getExpYear());
            card.put("cvc", model.getCvc());

            Map<String, Object> params = new HashMap<>();
            params.put("card", card);
            Token token = Token.create(params);

            if (token != null && token.getId() != null) {
                model.setSuccess(true);
                model.setToken(token.getId());
            }
            return model;
        } catch (StripeException e) {
            log.error("StripeService (createCardToken)", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * The charge function is used to charge a credit card using Stripe.
     *
     *
     * @param StripeChargeDto chargeRequest Pass the charge request to stripe
     *
     * @return A stripechargedto object
     *
     * @docauthor Trelent
     */
    public StripeChargeDto charge(StripeChargeDto chargeRequest) {
        try {
            chargeRequest.setSuccess(false);
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (chargeRequest.getAmount() * 100));
            chargeParams.put("currency", "USD");
            chargeParams.put("description", "Payment for id" + chargeRequest.getAdditionalInfo().getOrDefault("ID_TAG", ""));
            chargeParams.put("source", chargeRequest.getStripeToken());

            Map<String, Object> metaData = new HashMap<>();
            metaData.put("id", chargeRequest.getChargeId());
            metaData.putAll(chargeRequest.getAdditionalInfo());

            Charge charge = Charge.create(chargeParams);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

            if (charge.getPaid()) {
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(true);
            }
            return chargeRequest;
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

    }
}
