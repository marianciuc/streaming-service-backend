package zut.wi.streamingservice.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zut.wi.streamingservice.dto.request.*;
import zut.wi.streamingservice.dto.response.AuthenticationResponse;
import zut.wi.streamingservice.dto.response.MessageResponse;
import zut.wi.streamingservice.model.Category;
import zut.wi.streamingservice.model.Content;
import zut.wi.streamingservice.model.Genre;
import zut.wi.streamingservice.service.AuthenticationService;
import zut.wi.streamingservice.service.CategoryService;
import zut.wi.streamingservice.service.ContentService;
import zut.wi.streamingservice.service.GenreService;

@RestController
@RequestMapping("/content")
@AllArgsConstructor

public class ContentController {

    private final ContentService contentService;
    private final CategoryService categoryService;
    private final GenreService genreService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Content> authenticate(@RequestBody
                                                ContentRequest request) {
        return ResponseEntity.ok(contentService.createContent(request));
    }

    @PostMapping("/category")
    @ResponseBody
    public ResponseEntity<Category> authenticate(@RequestBody
                                                        CategoryRequest request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PostMapping("/genre")
    @ResponseBody
    public ResponseEntity<Genre> authenticate(@RequestBody
                                                        GenreRequest request) {
        return ResponseEntity.ok(genreService.createCategory(request));
    }

}
