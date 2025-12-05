package com.shrey.user_service.repository;

import com.shrey.user_service.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    List<User> findByNameContaining(String name);
    Optional<User> findUserById(Long id);
    List<User> findAll();
}
