package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import zut.wi.streamingservice.enums.SubscriptionStatus;

import java.util.Date;

@Entity
@Data
@Table(name = "user_subscriptions")
public class UserSubscription extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    SubscriptionStatus subscriptionStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "started_at", nullable = false)
    Date startedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired_at", nullable = false)
    Date expiredAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "canceled_at", nullable = true)
    Date canceledAt;
}
