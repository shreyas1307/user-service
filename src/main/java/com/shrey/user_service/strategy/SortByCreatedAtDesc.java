package com.shrey.user_service.strategy;

import com.shrey.user_service.model.User;

import java.util.Comparator;
import java.util.List;

public class SortByCreatedAtDesc implements UserSortStrategy {

    @Override
    public List<User> sort(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getCreatedAt).reversed())
                .toList();
    };
}
