package com.shrey.user_service.repository;

import com.shrey.user_service.model.User;
import java.util.List;

public interface UserRepository {
    User save(User user);
    List<User> findByNameContaining(String name);
    List<User> findAll();
}
