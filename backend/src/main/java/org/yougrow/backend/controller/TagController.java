package org.yougrow.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yougrow.backend.entities.Tag;
import org.yougrow.backend.entities.projection.Tag.TagDetail;
import org.yougrow.backend.entities.projection.Tag.TagShort;
import org.yougrow.backend.services.TagService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/tags")
@CrossOrigin("https://quizmaster-k1ej.onrender.com")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagShort>> getAllTags() {
        return ok(tagService.getAllTagsShort());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDetail> getTagById(@PathVariable Long id) {
        return tagService.getTagDetailById(id)
                .map(tag -> ok(tag))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tag> addTag(@RequestBody @Valid Tag tag) {
        if (tag.getId() != null) {
            return badRequest().build();
        }

        return ok(tagService.addTag(tag));
    }

    @PutMapping
    public ResponseEntity<Tag> updateTag(@RequestBody @Valid Tag tag) {
        if (tag.getId() == null) {
            return badRequest().build();
        }

        Optional<Tag> existingTag = tagService.getTagById(tag.getId());
        if (existingTag.isEmpty()) {
            return badRequest().build();
        }

        return ok(tagService.updateTag(tag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ok().build();
    }

}
