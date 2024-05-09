package zut.wi.streamingservice.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zut.wi.streamingservice.dto.StripeChargeDto;
import zut.wi.streamingservice.dto.StripeTokenDto;
import zut.wi.streamingservice.service.StripeService;

@RestController
@RequestMapping("/stripe")
@AllArgsConstructor
public class StripeApi {
    private final StripeService stripeService;

    @PostMapping("/card/token")
    @ResponseBody
    public StripeTokenDto createCardToken(@RequestBody StripeTokenDto model){
        return stripeService.createCardToken(model);
    }

    @PostMapping("/charge")
    @ResponseBody
    public StripeChargeDto charge(@RequestBody StripeChargeDto model){
        return stripeService.charge(model);
    }
}
