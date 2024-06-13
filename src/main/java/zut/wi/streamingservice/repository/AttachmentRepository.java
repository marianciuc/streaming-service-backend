package zut.wi.streamingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zut.wi.streamingservice.model.Attachment;
import zut.wi.streamingservice.model.Category;

import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
