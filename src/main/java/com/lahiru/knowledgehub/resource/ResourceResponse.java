package com.lahiru.knowledgehub.resource;

import java.util.UUID;

public record ResourceResponse(
        UUID id,
        UUID collectionId,
        String title,
        String url,
        String notes,
        ResourceType resourceType
) {
}