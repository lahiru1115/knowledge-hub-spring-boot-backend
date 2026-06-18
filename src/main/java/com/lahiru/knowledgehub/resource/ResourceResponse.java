package com.lahiru.knowledgehub.resource;

import com.lahiru.knowledgehub.tag.TagResponse;

import java.util.List;
import java.util.UUID;

public record ResourceResponse(
        UUID id,
        UUID collectionId,
        String title,
        String url,
        String notes,
        ResourceType resourceType,
        List<TagResponse> tags
) {
}