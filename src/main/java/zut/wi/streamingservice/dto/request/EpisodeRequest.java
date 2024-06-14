package zut.wi.streamingservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EpisodeRequest {
    private Integer number;
    private UUID seasonId;
    private List<UUID> attachment;
    private String previewPhotoUrl;
}
