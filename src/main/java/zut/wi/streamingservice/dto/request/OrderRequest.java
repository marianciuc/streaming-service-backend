package zut.wi.streamingservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zut.wi.streamingservice.enums.SubscriptionLevel;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
    private UUID subscriptionId;
}
