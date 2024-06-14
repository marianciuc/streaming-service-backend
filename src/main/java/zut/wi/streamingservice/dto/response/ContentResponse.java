package zut.wi.streamingservice.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zut.wi.streamingservice.model.AdditionalMedia;
import zut.wi.streamingservice.model.Category;
import zut.wi.streamingservice.model.Genre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentResponse {
    private UUID id;
    private String title;
    private Date date;
    private CategoryResponse category;
    private List<GenreResponse> genres;
    private Integer watches;
    private String posterUrl;
    private String backgroundUrl;
    private String small_poster_url;
    private List<AdditionalMediaResponse> additionalMedia;
}
