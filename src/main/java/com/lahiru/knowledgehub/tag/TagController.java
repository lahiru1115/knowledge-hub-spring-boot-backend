package com.lahiru.knowledgehub.tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public TagResponse create(
            @Valid @RequestBody TagRequest request
    ) {
        return tagService.create(request);
    }

    @GetMapping
    public List<TagResponse> getAll() {
        return tagService.getAll();
    }

    @GetMapping("/{id}")
    public TagResponse getOne(
            @PathVariable UUID id
    ) {
        return tagService.getOne(id);
    }

    @PutMapping("/{id}")
    public TagResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody TagRequest request
    ) {
        return tagService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(
            @PathVariable UUID id
    ) {
        tagService.delete(id);

        return Map.of(
                "message", "Tag deleted successfully"
        );
    }
}