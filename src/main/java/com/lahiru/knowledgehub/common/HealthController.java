package com.lahiru.knowledgehub.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/")
    public Map<String, String> root() {
        return Map.of(
                "message", "Knowledge Hub Spring Boot API"
        );
    }

    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of(
                "status", "ok"
        );
    }
}