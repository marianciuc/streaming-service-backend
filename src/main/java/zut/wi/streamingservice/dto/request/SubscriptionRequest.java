package zut.wi.streamingservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zut.wi.streamingservice.enums.SubscriptionLevel;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscriptionRequest {
    private String title;
    private String description;
    private Integer durationDays;
    private BigDecimal price;
    private SubscriptionLevel subscriptionLevel;
}
