package zut.wi.streamingservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zut.wi.streamingservice.dto.StripeChargeDto;
import zut.wi.streamingservice.dto.request.OrderRequest;
import zut.wi.streamingservice.model.Order;
import zut.wi.streamingservice.service.OrderService;
import zut.wi.streamingservice.service.StripeService;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/charge")
    @ResponseBody
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok().body(orderService.createOrder(request.getSubscriptionId()));
    }
}
