package com.lahiru.knowledgehub.collection;

import com.lahiru.knowledgehub.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @PostMapping
    public CollectionResponse create(
            @Valid @RequestBody CollectionRequest request,
            @AuthenticationPrincipal User user
    ) {
        return collectionService.create(request, user);
    }

    @GetMapping
    public List<CollectionResponse> getAll(
            @AuthenticationPrincipal User user
    ) {
        return collectionService.getAll(user);
    }

    @GetMapping("/{id}")
    public CollectionResponse getOne(
            @PathVariable UUID id,
            @AuthenticationPrincipal User user
    ) {
        return collectionService.getOne(id, user);
    }

    @PutMapping("/{id}")
    public CollectionResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody CollectionRequest request,
            @AuthenticationPrincipal User user
    ) {
        return collectionService.update(id, request, user);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(
            @PathVariable UUID id,
            @AuthenticationPrincipal User user
    ) {
        collectionService.delete(id, user);

        return Map.of(
                "message", "Collection deleted successfully"
        );
    }
}