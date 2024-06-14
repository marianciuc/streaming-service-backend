package zut.wi.streamingservice.controllers.subscribed;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zut.wi.streamingservice.service.ContentService;

@RestController
@RequestMapping("/subscribed/content")
@AllArgsConstructor
public class ContentSubscribedController {
    private final ContentService contentService;

    @GetMapping
    public ResponseEntity<Object> findContent(@RequestParam(defaultValue = "") String title,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok().body(contentService.findByTitleContains(title, page, size));
    }
}
