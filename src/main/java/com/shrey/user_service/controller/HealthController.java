package com.shrey.user_service.controller;

import com.shrey.user_service.dto.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public HealthResponse getHealth() {
        return new HealthResponse("UP", Instant.now().toEpochMilli());
    }

}
