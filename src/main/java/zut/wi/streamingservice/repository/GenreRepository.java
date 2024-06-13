package zut.wi.streamingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zut.wi.streamingservice.model.Category;
import zut.wi.streamingservice.model.Genre;

import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID> {

}
