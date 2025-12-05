package com.shrey.user_service.repository;

import com.shrey.user_service.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final Map<Long, User> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public InMemoryUserRepository() {
    }

    @Override
    public User save(User user) {
        if(user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }
        storage.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> findByNameContaining(String name) {
        return storage
                .values()
                .stream()
                .filter(user -> nameContainsIgnoreCase(user, name))
                .collect(Collectors.toList());
    };

    @Override
    public List<User> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(storage.get(id));
    };

    private Boolean nameContainsIgnoreCase(User user, String substring) {
        return user.getName() != null && user.getName().toLowerCase().contains(substring.toLowerCase());
    };
}
