package zut.wi.streamingservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zut.wi.streamingservice.dto.StripeChargeDto;
import zut.wi.streamingservice.dto.request.AttachmentRequest;
import zut.wi.streamingservice.model.Attachment;
import zut.wi.streamingservice.model.User;
import zut.wi.streamingservice.service.AttachmentService;
import zut.wi.streamingservice.service.StripeService;

import java.util.UUID;

@RestController
@RequestMapping("/attachment")
@AllArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping
    public Attachment upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("description") String description,
            @RequestParam("title") String title,
            @RequestParam("scale") String scale,
            @RequestParam("isVideo") boolean isVideo) {
        AttachmentRequest attachmentRequest = AttachmentRequest.builder()
                .description(description)
                .title(title)
                .isVideo(isVideo)
                .scale(scale)
                .build();
        return attachmentService.saveAttachment(file, attachmentRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> download( @PathVariable UUID id) {
        Attachment attachment = attachmentService.getAttachment(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachment.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
}
