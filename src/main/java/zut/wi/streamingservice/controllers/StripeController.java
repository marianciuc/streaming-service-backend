package zut.wi.streamingservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zut.wi.streamingservice.dto.StripeChargeDto;
import zut.wi.streamingservice.dto.StripeTokenDto;
import zut.wi.streamingservice.service.StripeService;

@RestController
@RequestMapping("/stripe")
@AllArgsConstructor
public class StripeController {
    private final StripeService stripeService;

    @PostMapping("/charge")
    @ResponseBody
    public StripeChargeDto charge(@RequestBody StripeChargeDto model) {
        return stripeService.charge(model);
    }
}
