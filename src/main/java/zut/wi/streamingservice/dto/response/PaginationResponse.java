package zut.wi.streamingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse<T> {
    private List<T> data;
    private int currentPage;
    private Long totalItems;
    private int totalPages;
}
