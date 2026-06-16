package com.lahiru.knowledgehub.auth;

public record LoginResponse(
        String accessToken,
        String tokenType
) {
}