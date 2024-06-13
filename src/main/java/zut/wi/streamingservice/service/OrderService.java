package zut.wi.streamingservice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.enums.OrderStatus;
import zut.wi.streamingservice.exceptions.InsufficientFundsException;
import zut.wi.streamingservice.model.Order;
import zut.wi.streamingservice.model.Subscription;
import zut.wi.streamingservice.model.User;
import zut.wi.streamingservice.repository.OrderRepository;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class OrderService {
    OrderRepository orderRepository;
    UserService userService;
    SubscriptionService subscriptionService;
    PaymentService paymentService;

    @Transactional
    public Order createOrder(UUID subscriptionId) {
        try {
            Subscription subscription = subscriptionService.getSubscription(subscriptionId);
            User user = userService.getUserContext();

            Order order = Order.builder()
                    .orderStatus(OrderStatus.CREATED)
                    .subscription(subscription)
                    .user(user)
                    .build();

            order = orderRepository.save(order);
            try {
                paymentService.debitAccount(subscription, user);
                order.setOrderStatus(OrderStatus.COMPLETED);
            } catch (InsufficientFundsException exception) {
                order.setOrderStatus(OrderStatus.FAILED);
                orderRepository.save(order);
                throw new InsufficientFundsException("Insufficient funds for order creation.");
            }

            return orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException("Error during order creation.", e);
        }
    }
}
