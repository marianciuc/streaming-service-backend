package zut.wi.streamingservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zut.wi.streamingservice.dto.StripeChargeDto;
import zut.wi.streamingservice.dto.request.SubscriptionRequest;
import zut.wi.streamingservice.dto.response.AuthenticationResponse;
import zut.wi.streamingservice.model.Subscription;
import zut.wi.streamingservice.service.StripeService;
import zut.wi.streamingservice.service.SubscriptionService;

@RestController
@RequestMapping("/subscription")
@AllArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Subscription> create(@RequestBody SubscriptionRequest request) {
        return ResponseEntity.ok(subscriptionService.createSubscription(request));
    }
}
