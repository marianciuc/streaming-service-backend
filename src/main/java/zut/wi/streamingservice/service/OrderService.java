package zut.wi.streamingservice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.enums.OrderStatus;
import zut.wi.streamingservice.exceptions.InsufficientFundsException;
import zut.wi.streamingservice.exceptions.UserAlreadyHasSubscription;
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
        Subscription subscription = null;
        User user = null;
        Order order = null;

        try {
            subscription = subscriptionService.getSubscription(subscriptionId);
            user = userService.getUserContext();

            if (user.getActiveSubscription() != null) {
                throw new UserAlreadyHasSubscription("The user already has an active subscription.");
            }

            order = Order.builder()
                    .orderStatus(OrderStatus.CREATED)
                    .subscription(subscription)
                    .user(user)
                    .build();

            order = orderRepository.save(order);
            try {
                paymentService.debitAccount(subscription, user);
                order.setOrderStatus(OrderStatus.COMPLETED);
                userService.enableSubscription(user);
            } catch (InsufficientFundsException exception) {
                order.setOrderStatus(OrderStatus.FAILED);
                orderRepository.save(order);
                throw exception;
            }

            return orderRepository.save(order);
        } catch (InsufficientFundsException | UserAlreadyHasSubscription e) {
            if (order != null) {
                order.setOrderStatus(OrderStatus.FAILED);
                orderRepository.save(order);
            }
            throw e;
        } catch (Exception e) {
            log.error("Error during order creation.", e);
            if (order != null) {
                order.setOrderStatus(OrderStatus.FAILED);
                orderRepository.save(order);
            }
            throw new RuntimeException("Error during order creation.", e);
        }
    }
}
