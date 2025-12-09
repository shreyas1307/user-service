package com.shrey.user_service.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Instant createdAt;

    public UserResponse() {};

    public UserResponse(Long id, String name, String email, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }
}
