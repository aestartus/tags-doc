package com.estartus.tags.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
