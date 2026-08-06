package com.campusconnect.campusconnect.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class HealthController {

    private final Instant startTime = Instant.now();

    @Value("${spring.application.name:campusconnect}")
    private String appName;

    @GetMapping("/health")
    public Map<String, Object> healthCheck() {
        long uptime = java.time.Duration.between(startTime, Instant.now()).toMillis();
        return Map.of(
            "status", "UP",
            "application", appName,
            "uptime", uptime,
            "timestamp", Instant.now().toString()
        );
    }
}
