package zut.wi.streamingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zut.wi.streamingservice.model.Category;
import zut.wi.streamingservice.model.Content;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
    @Override
    Optional<Content> findById(UUID uuid);

    @Override
    List<Content> findAll();

    List<Content> findAllByCategory(Category category);
}
