package com.lahiru.knowledgehub.auth;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email
) {
}