package com.shrey.user_service.controller;

import com.shrey.user_service.dto.UserRequest;
import com.shrey.user_service.dto.UserResponse;
import com.shrey.user_service.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(@jakarta.validation.Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    };

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/search")
    public List<UserResponse> searchUsers(@RequestParam String name) {
        return userService.searchUsers(name);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
