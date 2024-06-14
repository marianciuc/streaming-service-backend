package zut.wi.streamingservice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.dto.request.EpisodeRequest;
import zut.wi.streamingservice.enums.OrderStatus;
import zut.wi.streamingservice.exceptions.InsufficientFundsException;
import zut.wi.streamingservice.exceptions.NotFoundException;
import zut.wi.streamingservice.exceptions.UserAlreadyHasSubscription;
import zut.wi.streamingservice.model.*;
import zut.wi.streamingservice.repository.EpisodeRepository;
import zut.wi.streamingservice.repository.OrderRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EpisodeService {
    private EpisodeRepository episodeRepository;
    private AttachmentService attachmentService;
    private SeasonService seasonService;

    public Episode addEpisode(EpisodeRequest request) {
        try {
            Season season = seasonService.getSeasonById(request.getSeasonId());
            if (season == null) {
                throw new NotFoundException("Content with the given ID not found");
            }
            List<Attachment> attachments = request.getAttachment().stream().map((id) ->
                            attachmentService.getAttachment(id))
                    .collect(Collectors.toList());

            if (attachments.isEmpty()) throw new RuntimeException("Request error: Episodes not provided");

            Episode episode = Episode.builder()
                    .attachment(attachments)
                    .number(request.getNumber())
                    .season(season)
                    .previewPhotoUrl(request.getPreviewPhotoUrl())
                    .build();
            return episodeRepository.save(episode);
        } catch (Exception e) {
            log.error("Error adding an episode", e);
            throw new RuntimeException("Error adding an episode.", e);
        }
    }
}
