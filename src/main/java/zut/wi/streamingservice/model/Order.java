package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import zut.wi.streamingservice.enums.OrderStatus;

import java.math.BigDecimal;

@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Order extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 30, columnDefinition = "varchar(30) default 'CREATED'")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private OrderStatus orderStatus = OrderStatus.CREATED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
