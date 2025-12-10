package com.shrey.user_service.strategy;

import com.shrey.user_service.model.User;

import java.util.List;

public interface UserSortStrategy {
    List<User> sort(List<User> users);
}
