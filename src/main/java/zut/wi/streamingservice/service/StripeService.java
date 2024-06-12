package zut.wi.streamingservice.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.dto.StripeChargeDto;
import zut.wi.streamingservice.dto.StripeTokenDto;
import zut.wi.streamingservice.exceptions.PaymentDeclinedError;
import zut.wi.streamingservice.model.Payment;
import zut.wi.streamingservice.model.User;
import zut.wi.streamingservice.repository.PaymentsRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
@RequiredArgsConstructor
public class StripeService {

    @Value("${api.stripe.key}")
    private String stripeApiKey;

    private PaymentService paymentService;


    /**
     * The init function is called by the Spring Framework when it starts up.
     * It sets the Stripe API key to be used for all requests made from this application.
     *
     * @return Nothing
     * @docauthor Trelent
     */
    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }


    /**
     * The charge function is used to charge a credit card using Stripe.
     *
     * @param StripeChargeDto chargeRequest Pass the charge request to stripe
     * @return A stripechargedto object
     * @docauthor Trelent
     */
    public StripeChargeDto charge(StripeChargeDto chargeRequest) {
        try {
            chargeRequest.setSuccess(false);
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (chargeRequest.getAmount() * 100));
            chargeParams.put("currency", chargeRequest.getCurrency());
            chargeParams.put("description", "Payment for user " + chargeRequest.getUsername());
            chargeParams.put("source", chargeRequest.getStripeToken());

            Map<String, Object> metaData = new HashMap<>();
            metaData.put("id", chargeRequest.getChargeId());
            metaData.putAll(chargeRequest.getAdditionalInfo());

            chargeParams.put("metadata", metaData);

            Charge charge = Charge.create(chargeParams);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

            if (charge.getPaid()) {
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(true);
                paymentService.savePayment(charge);
            } else {
                throw new PaymentDeclinedError("Payment declined.");
            }
            return chargeRequest;
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
