package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import zut.wi.streamingservice.enums.PaymentStatus;

import java.math.BigDecimal;

@Table(name = "payments")
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity{
    @Column(name = "payment_status"
    )
    private String paymentStatus;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "description")
    private String description;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "message")
    private String message;

    @ManyToOne
    private User user;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL
    }
}
