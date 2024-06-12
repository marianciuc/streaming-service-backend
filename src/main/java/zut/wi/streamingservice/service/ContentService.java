package zut.wi.streamingservice.service;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.dto.request.ContentRequest;
import zut.wi.streamingservice.model.Category;
import zut.wi.streamingservice.model.Content;
import zut.wi.streamingservice.model.Genre;
import zut.wi.streamingservice.repository.ContentRepository;
import zut.wi.streamingservice.utilities.URLValidator;

import java.util.ArrayList;
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
}
