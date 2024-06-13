package zut.wi.streamingservice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.dto.request.SubscriptionRequest;
import zut.wi.streamingservice.exceptions.SubscriptionNotFoundException;
import zut.wi.streamingservice.model.Subscription;
import zut.wi.streamingservice.repository.SubscriptionRepository;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class SubscriptionService {
    SubscriptionRepository subscriptionRepository;

    @Transactional
    public Subscription createSubscription(SubscriptionRequest request) {
        try {
            Subscription subscription = Subscription.builder()
                    .subscriptionLevel(request.getSubscriptionLevel())
                    .price(request.getPrice())
                    .durationDays(request.getDurationDays())
                    .description(request.getDescription())
                    .title(request.getTitle())
                    .build();
            return subscriptionRepository.save(subscription);
        } catch (Exception e) {
            String errorMessage = "Error when adding a subscription.";
            log.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public Subscription getSubscription(UUID id) {
        try {
            return subscriptionRepository.findById(id)
                    .orElseThrow(() -> new SubscriptionNotFoundException("Subscription with id " + id + " not found"));
        } catch (SubscriptionNotFoundException e) {
            throw e;
        } catch (Exception e) {
            String errorMessage = "Error when searching for a subscription in the database.";
            log.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }
}
