package com.shrey.user_service.controller;

import com.shrey.user_service.dto.UserRequest;
import com.shrey.user_service.dto.UserResponse;
import com.shrey.user_service.service.UserService;
import com.shrey.user_service.strategy.SortByCreatedAtDesc;
import com.shrey.user_service.strategy.SortByNameAsc;
import com.shrey.user_service.strategy.UserSortStrategy;
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

    @GetMapping("/sorted")
    public List<UserResponse> getSortedUsers(@RequestParam String strategy) {
        UserSortStrategy sortStrategy;

        switch (strategy.toLowerCase()) {
            case "name":
                sortStrategy = new SortByNameAsc();
                break;
            case "recent":
                sortStrategy = new SortByCreatedAtDesc();
                break;
            default:
                throw new IllegalArgumentException("Unknown sort strategy");
        };

        return userService.getAllUsersSorted(sortStrategy);
    };
}
