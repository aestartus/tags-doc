package com.estartus.auth.service;

import com.estartus.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);
}
