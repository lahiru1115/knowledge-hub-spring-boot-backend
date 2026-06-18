package com.lahiru.knowledgehub.resource;

import com.lahiru.knowledgehub.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResourceResponse create(
            @Valid @RequestBody ResourceRequest request,
            @AuthenticationPrincipal User user
    ) {
        return resourceService.create(request, user);
    }

    @GetMapping
    public List<ResourceResponse> getAll(
            @AuthenticationPrincipal User user
    ) {
        return resourceService.getAll(user);
    }

    @GetMapping("/{id}")
    public ResourceResponse getOne(
            @PathVariable UUID id,
            @AuthenticationPrincipal User user
    ) {
        return resourceService.getOne(id, user);
    }

    @PutMapping("/{id}")
    public ResourceResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody ResourceRequest request,
            @AuthenticationPrincipal User user
    ) {
        return resourceService.update(id, request, user);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(
            @PathVariable UUID id,
            @AuthenticationPrincipal User user
    ) {
        resourceService.delete(id, user);

        return Map.of(
                "message", "Resource deleted successfully"
        );
    }
}