package com.internship.stocktrading.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internship.stocktrading.model.User;
import com.internship.stocktrading.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
})
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepo.findById(id).orElseThrow();
    }
}
