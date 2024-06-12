package zut.wi.streamingservice.service;

import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.dto.request.GenreRequest;
import zut.wi.streamingservice.model.Genre;
import zut.wi.streamingservice.model.Payment;
import zut.wi.streamingservice.model.User;
import zut.wi.streamingservice.repository.GenreRepository;
import zut.wi.streamingservice.repository.PaymentsRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentService {
    PaymentsRepository paymentsRepository;
    UserService userService;

    public void savePayment(Charge charge) {
        User user = userService.getUserContext();
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
    }
}
