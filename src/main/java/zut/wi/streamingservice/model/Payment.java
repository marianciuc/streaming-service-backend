package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import zut.wi.streamingservice.enums.PaymentStatus;

import java.math.BigDecimal;

@Table(name = "payments")
@Data
@Entity
public class Payment extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", length = 30)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private PaymentStatus paymentStatus;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "description")
    private String description;

    @Column(name = "source")
    private String source;

    @Column(name = "payment_id")
    private String paymentId;

    @OneToOne
    Order order;

    @ManyToOne
    private User user;
}
