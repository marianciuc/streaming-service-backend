package zut.wi.streamingservice.service;


import com.stripe.exception.InvalidRequestException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.dto.request.ContentRequest;
import zut.wi.streamingservice.dto.response.*;
import zut.wi.streamingservice.exceptions.NotFoundException;
import zut.wi.streamingservice.model.Category;
import zut.wi.streamingservice.model.Content;
import zut.wi.streamingservice.model.Genre;
import zut.wi.streamingservice.repository.ContentRepository;
import zut.wi.streamingservice.utilities.URLValidator;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContentService {
    ContentRepository contentRepository;

    CategoryService categoryService;
    GenreService genreService;


    public Content createContent(ContentRequest request) {
        try {
            Category category = categoryService.findById(request.getCategory());
            List<Genre> genres = request.getGenres().stream()
                    .map(genreUUID -> genreService.findById(genreUUID))
                    .toList();

            if (genres.isEmpty()) throw new RuntimeException("You can't create content without genres");
            if (category == null) throw new RuntimeException("You can't create content without category");
            if (!URLValidator.isValidURL(request.getPosterUrl())
                    || !URLValidator.isValidURL(request.getSmallPosterUrl())
                    || !URLValidator.isValidURL(request.getBackgroundUrl())) {
                throw new RuntimeException("The URL is not valid");
            }

            Content content = Content.builder()
                    .genres(genres)
                    .category(category)
                    .backgroundUrl(request.getBackgroundUrl())
                    .date(request.getDate())
                    .title(request.getTitle())
                    .small_poster_url(request.getSmallPosterUrl())
                    .description(request.getDescription())
                    .posterUrl(request.getPosterUrl())
                    .build();
            return contentRepository.save(content);
        } catch (Exception e) {
            throw new RuntimeException("Error creating content.", e);
        }
    }

    public Content getContentById(UUID id) {
        return contentRepository.findById(id).orElseThrow(() -> new NotFoundException("Content not found."));
    }

    public PaginationResponse<ContentResponse> findByTitleContains(String title, int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Content> pageResult = contentRepository.findByTitleContains(title, paging);

            List<ContentResponse> contentDTOs = pageResult.getContent().stream()
                    .map(this::convertToDto)
                    .toList();

            long totalElements = pageResult.getTotalElements();
            int totalPages = pageResult.getTotalPages();
            int currentPage = pageResult.getNumber();

            return new PaginationResponse<>(contentDTOs, currentPage, totalElements, totalPages);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Invalid pagination parameters");
        } catch (Exception e) {
            throw new NotFoundException("Content not found");
        }
    }

    private ContentResponse convertToDto(Content content) {
        List<AdditionalMediaResponse> additionalMediaResponse = content.getAdditionalMedia().stream()
                .map(m -> {
                    return AdditionalMediaResponse.builder()
                            .url(m.getUrl())
                            .id(m.getId())
                            .type(m.getType())
                            .build();
                })
                .toList();
        List<GenreResponse> genresResponse = content.getGenres().stream()
                .map(m -> {
                    return GenreResponse.builder()
                            .id(m.getId())
                            .description(m.getDescription())
                            .title(m.getTitle())
                            .build();
                })
                .toList();
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(content.getCategory().getId())
                .title(content.getCategory().getName())
                .description(content.getCategory().getDescription())
                .build();
        return ContentResponse.builder()
                .id(content.getId())
               .additionalMedia(additionalMediaResponse)
               .watches(content.getWatches())
               .backgroundUrl(content.getBackgroundUrl())
               .small_poster_url(content.getSmall_poster_url())
               .title(content.getTitle())
               .category(categoryResponse)
               .genres(genresResponse)
               .date(content.getDate())
               .build();
    }
}
