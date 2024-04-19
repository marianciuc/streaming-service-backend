package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import zut.wi.streamingservice.enums.PaymentStatus;

@Table(name = "payments")
@Data
@Getter
@Setter
@Entity
public class Payment extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", length = 30)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private PaymentStatus paymentStatus;
}
