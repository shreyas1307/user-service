package com.shrey.user_service.service;

import com.shrey.user_service.dto.UserRequest;
import com.shrey.user_service.dto.UserResponse;
import com.shrey.user_service.exception.UserNotFoundException;
import com.shrey.user_service.model.User;
import com.shrey.user_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    @Test
    void getUserById_shouldReturnUserResponse_whenUserExists() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        UserService service = new UserService(repo);

        User user = new User(1L, "Shrey", "shreyas@example.com", Instant.now());
        Mockito.when(repo.findUserById(1L)).thenReturn(Optional.of(user));

        UserResponse response = service.getUserById(1L);

        assertEquals(1L, response.getId());
        assertEquals("Shrey", response.getName());
        assertEquals("shreyas@example.com", response.getEmail());
    }

    @Test
    void getUserById_shouldThrowUserNotFoundException() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        UserService service = new UserService(repo);

        Mockito.when(repo.findUserById(99L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.getUserById(99L));
    }

    @Test
    void createUser_shouldReturnUserResponseUponSuccess() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        UserService service = new UserService(repo);

        UserRequest request = new UserRequest("Shrey", "shrey@example.com");

        Mockito.when(repo.save(Mockito.any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(1L);
            return u;
        });

        UserResponse response = service.createUser(request);

        assertEquals("Shrey", response.getName());
        assertEquals("shrey@example.com", response.getEmail());
        assertEquals(1L, response.getId());
    }

    @Test
    void searchUsers_shouldReturnMappedResponses() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        UserService service = new UserService(repo);

        User user1 = new User(1L, "Shrey", "shrey@example.com", Instant.now());
        User user2 = new User(2L, "Shreyas", "shreyas@example.com", Instant.now());

        Mockito.when(repo.findByNameContaining("rey")).thenReturn(List.of(user1, user2));

        List<UserResponse> result = service.searchUsers("rey");

        assertEquals(2, result.size());
        assertEquals("Shrey", result.get(0).getName());
    }

    @Test
    void getAllUsers_shouldReturnMappedResponses() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        UserService service = new UserService(repo);

        User user1 = new User(1L, "Shrey", "shrey@example.com", Instant.now());
        User user2 = new User(2L, "Sam", "sam@example.com", Instant.now());
        User user3 = new User(3L, "Alex", "alex@example.com", Instant.now());
        User user4 = new User(4L, "Bob", "bob@example.com", Instant.now());

        Mockito.when(repo.findAll()).thenReturn(List.of(user1, user2, user3, user4));

        List<UserResponse> response = service.getAllUsers();

        assertEquals(4, response.size());
    }
}
