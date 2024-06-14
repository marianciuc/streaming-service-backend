package zut.wi.streamingservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zut.wi.streamingservice.model.Category;
import zut.wi.streamingservice.model.Content;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContentRepository extends JpaRepository<Content, UUID> {
    List<Content> findAllByCategory(Category category);
    List<Content> findByTitleContains(String title);
    Page<Content> findByTitleContains(String title, Pageable pageable);
}
