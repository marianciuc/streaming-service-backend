package zut.wi.streamingservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentRequest {
    private String title;
    private String description;
    private String scale;
    private boolean isVideo;
}
