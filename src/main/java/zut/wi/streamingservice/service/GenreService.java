package zut.wi.streamingservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.dto.request.GenreRequest;
import zut.wi.streamingservice.model.Genre;
import zut.wi.streamingservice.repository.GenreRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GenreService {
    GenreRepository genreRepository;

    public Genre createCategory(GenreRequest request) {
        try {
            Genre genre = Genre.builder()
                    .title(request.getTitle())
                    .description(request.getDescription())
                    .build();
            return genreRepository.save(genre);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create content", e);
        }
    }

    public Genre findById(UUID id) {
        try {
            return genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre with this id is not found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Genre> findAll() {
        try {
            return genreRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
