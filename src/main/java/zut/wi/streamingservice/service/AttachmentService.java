package zut.wi.streamingservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zut.wi.streamingservice.dto.request.AttachmentRequest;
import zut.wi.streamingservice.model.Attachment;
import zut.wi.streamingservice.repository.AttachmentRepository;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class AttachmentService {
    private AttachmentRepository attachmentRepository;

    public Attachment saveAttachment(MultipartFile file, AttachmentRequest request){
        try {
            Attachment attachment = Attachment.builder()
                    .isVideo(request.isVideo())
                    .description(request.getDescription())
                    .fileType(file.getContentType())
                    .fileName(request.getTitle())
                    .data(file.getBytes())
                    .fileSize(file.getSize())
                    .build();
            if (request.isVideo()) attachment.setScale(request.getScale());
            attachment = attachmentRepository.save(attachment);
            attachment.setUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("attachment/").path(String.valueOf(attachment.getId())).toUriString());
            return attachmentRepository.save(attachment);
        } catch (Exception e){
            throw new RuntimeException("Error during file upload.");
        }
    }

    public Attachment getAttachment(UUID id){
        try {
             return attachmentRepository.getById(id);
        } catch (Exception e){
            throw new RuntimeException("File not found");
        }
    }
}
