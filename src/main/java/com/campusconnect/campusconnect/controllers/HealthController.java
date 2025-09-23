package com.campusconnect.campusconnect.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    
    @GetMapping("/health")
    public String healthCheck() {
        return "Campus Connect App is running";
    }
}
