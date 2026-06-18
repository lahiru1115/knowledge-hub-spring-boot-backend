package com.lahiru.knowledgehub.collection;

import java.util.UUID;

public record CollectionResponse(
        UUID id,
        String name,
        String description
) {
}