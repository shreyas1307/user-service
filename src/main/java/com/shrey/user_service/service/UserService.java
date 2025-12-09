package com.shrey.user_service.service;

import com.shrey.user_service.dto.UserRequest;
import com.shrey.user_service.dto.UserResponse;
import com.shrey.user_service.exception.UserNotFoundException;
import com.shrey.user_service.model.User;
import com.shrey.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setCreatedAt(Instant.now());

        User saved = userRepository.save(user);
        return toResponse(saved);
    };

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<UserResponse> searchUsers(String name) {
        return userRepository.findByNameContaining(name)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    };

    public UserResponse getUserById(Long id) {
        return userRepository.findUserById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }


}
