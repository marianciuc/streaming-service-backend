package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zut.wi.streamingservice.enums.SubscriptionLevel;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscriptions")
public class Subscription extends BaseEntity{
    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private SubscriptionLevel subscriptionLevel;

    @Column(name = "duration_days", nullable = false)
    private int durationDays;
}
