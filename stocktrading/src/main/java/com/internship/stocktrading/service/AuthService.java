package com.internship.stocktrading.service;

import org.springframework.stereotype.Service;
import com.internship.stocktrading.model.User;
import com.internship.stocktrading.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository repo;

    public AuthService(UserRepository repo) {
        this.repo = repo;
    }

    public User register(User user) {
        return repo.save(user);
    }
}
