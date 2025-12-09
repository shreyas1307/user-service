package com.shrey.user_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthResponse {
    private String status;
    private long timestamp;

    public HealthResponse(){};

    public HealthResponse(String status, long timestamp) {
        this.status = status;
        this.timestamp = timestamp;
    }
}
