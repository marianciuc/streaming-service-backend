package zut.wi.streamingservice.controllers.moderation;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zut.wi.streamingservice.dto.request.*;
import zut.wi.streamingservice.model.Category;
import zut.wi.streamingservice.model.Content;
import zut.wi.streamingservice.model.Episode;
import zut.wi.streamingservice.model.Genre;
import zut.wi.streamingservice.service.*;

@RestController
@RequestMapping("/moderation/content")
@AllArgsConstructor
//@PreAuthorize("hasRole('ADMIN')")
public class ContentModeratorController {

    private final ContentService contentService;
    private final CategoryService categoryService;
    private final GenreService genreService;
    private final EpisodeService episodeService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Content> addContent(@RequestBody
                                                ContentRequest request) {
        return ResponseEntity.ok(contentService.createContent(request));
    }

    @PostMapping("/category")
    @ResponseBody
    public ResponseEntity<Category> addCategory(@RequestBody
                                                        CategoryRequest request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PostMapping("/genre")
    @ResponseBody
    public ResponseEntity<Genre> addGenre(@RequestBody
                                                        GenreRequest request) {
        return ResponseEntity.ok(genreService.createCategory(request));
    }

    @PostMapping("/episode")
    @ResponseBody
    public ResponseEntity<Episode> addEpisode(@RequestBody
                                              EpisodeRequest request) {
        return ResponseEntity.ok(episodeService.addEpisode(request));
    }

    @PostMapping("/season")
    @ResponseBody
    public ResponseEntity<Episode> addSeason(@RequestBody
                                              EpisodeRequest request) {
        return ResponseEntity.ok(episodeService.addEpisode(request));
    }

}
