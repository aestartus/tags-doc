package com.estartus.tags.service;

import com.estartus.tags.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);
}
