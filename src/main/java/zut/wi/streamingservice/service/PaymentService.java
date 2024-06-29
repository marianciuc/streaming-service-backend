package zut.wi.streamingservice.service;

import com.stripe.model.Charge;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.exceptions.InsufficientFundsException;
import zut.wi.streamingservice.model.Payment;
import zut.wi.streamingservice.model.Subscription;
import zut.wi.streamingservice.model.User;
import zut.wi.streamingservice.repository.PaymentsRepository;

import java.math.BigDecimal;
import java.util.Date;

@Service
@AllArgsConstructor
public class PaymentService {
    private PaymentsRepository paymentsRepository;
    private UserService userService;
    private NotificationService notificationService;

    public void savePayment(Charge charge) {
        User user = userService.getUserContext();
        BigDecimal amount = new BigDecimal(charge.getAmount() / 100);
        paymentsRepository.save(Payment
                .builder()
                .currency(charge.getCurrency())
                .paymentId(charge.getId())
                .paymentStatus(charge.getStatus())
                .user(user)
                .description(charge.getDescription())
                .message(charge.getFailureMessage())
                .transactionType(Payment.TransactionType.DEPOSIT)
                .amount(new BigDecimal(charge.getAmount() / 100)).build());
        try {
            notificationService.sendPaymentSuccess(
                    user.getEmail(),
                    user.getFirstName() + " " + user.getLastName(),
                    new Date().toString(),
                    String.valueOf(amount),
                    String.valueOf(user.calculateBalance()));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void debitAccount(Subscription subscription, User user) throws InsufficientFundsException{
        if (user.calculateBalance().compareTo(subscription.getPrice()) < 0) {
            throw new InsufficientFundsException("Insufficient funds for this operation");
        }
        Payment.builder()
                .amount(subscription.getPrice())
                .description("Funds debited for " + subscription.getTitle())
                .currency("EUR")
                .transactionType(Payment.TransactionType.WITHDRAWAL)
                .user(user)
                .build();

    }
}
