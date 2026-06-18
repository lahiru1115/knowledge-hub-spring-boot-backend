package com.lahiru.knowledgehub.tag;

import java.util.UUID;

public record TagResponse(
        UUID id,
        String name
) {
}