package zut.wi.streamingservice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.dto.request.SeasonRequest;
import zut.wi.streamingservice.enums.OrderStatus;
import zut.wi.streamingservice.exceptions.InsufficientFundsException;
import zut.wi.streamingservice.exceptions.NotFoundException;
import zut.wi.streamingservice.exceptions.UserAlreadyHasSubscription;
import zut.wi.streamingservice.model.*;
import zut.wi.streamingservice.repository.OrderRepository;
import zut.wi.streamingservice.repository.SeasonRepository;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class SeasonService {
    SeasonRepository seasonRepository;
    ContentService contentService;

    public Season addSeason(SeasonRequest request){
        try {
            Content content = contentService.getContentById(request.getContentId());

            if (content == null) {
                throw new NotFoundException("Content with the given ID not found");
            }

            Season season = Season.builder()
                    .title(request.getTitle())
                    .description(request.getDescription())
                    .releseDate(request.getReleseDate())
                    .number(request.getNumber())
                    .content(content)
                    .build();

            return seasonRepository.save(season);
        } catch (NotFoundException e) {
            log.error("Content not found for ID: {}", request.getContentId(), e);
            throw e;
        } catch (Exception e) {
            log.error("Error creating season", e);
            throw new RuntimeException("Error creating season", e);
        }
    }

    public Season getSeasonById(UUID id){
        return seasonRepository.findById(id).orElseThrow(() -> new NotFoundException("Content not found."));
    }

}
