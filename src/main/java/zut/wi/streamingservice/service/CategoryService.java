package zut.wi.streamingservice.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.dto.request.CategoryRequest;
import zut.wi.streamingservice.model.Category;
import zut.wi.streamingservice.model.Genre;
import zut.wi.streamingservice.repository.CategoryRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {
    CategoryRepository categoryRepository;

    public Category createCategory(CategoryRequest request) {
        try {
            Category category = Category.builder()
                    .description(request.getDescription())
                    .name(request.getTitle())
                    .build();
            return categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("This name is already in use", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create category", e);
        }
    }

    public Category findById(UUID id) {
        try {
            return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category with this id is not found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> findAll() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
