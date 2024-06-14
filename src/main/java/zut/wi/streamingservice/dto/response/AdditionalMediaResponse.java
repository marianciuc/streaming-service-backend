package zut.wi.streamingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zut.wi.streamingservice.enums.AdditionalMediaType;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalMediaResponse {
    private AdditionalMediaType type;
    private String url;
    private UUID id;
}
