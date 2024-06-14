package zut.wi.streamingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zut.wi.streamingservice.model.Attachment;
import zut.wi.streamingservice.model.Episode;
import zut.wi.streamingservice.service.EpisodeService;

import java.util.UUID;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, UUID> {
}
