package com.shrey.user_service.repository;

import com.shrey.user_service.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {
    User save(User user);
    List<User> findAll();
}
