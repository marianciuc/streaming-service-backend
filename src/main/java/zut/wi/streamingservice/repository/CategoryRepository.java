package zut.wi.streamingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zut.wi.streamingservice.model.Category;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
