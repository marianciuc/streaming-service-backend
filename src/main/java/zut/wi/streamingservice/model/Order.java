package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import zut.wi.streamingservice.enums.OrderStatus;

import java.math.BigDecimal;

@Table(name = "orders")
@Data
@Getter
@Setter
@Entity
public class Order extends BaseEntity{
    @Column(name = "order_number", unique = true)
    private Integer orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 30, columnDefinition = "varchar(30) default 'CREATED'")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private OrderStatus orderStatus = OrderStatus.CREATED;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @ManyToOne
    private User user;
}
