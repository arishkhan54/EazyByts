package com.internship.stocktrading.controller;

import org.springframework.web.bind.annotation.*;

import com.internship.stocktrading.dto.AuthResponse;
import com.internship.stocktrading.model.User;
import com.internship.stocktrading.repository.UserRepository;
import com.internship.stocktrading.security.JwtUtil;
import com.internship.stocktrading.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService service;
    private final UserRepository repo;
    private final JwtUtil jwt;

    public AuthController(AuthService service,
                          UserRepository repo,
                          JwtUtil jwt) {
        this.service = service;
        this.repo = repo;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) {

    User u = repo.findByEmail(user.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!u.getPassword().equals(user.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }

    String token = jwt.generateToken(u.getEmail());

    return new AuthResponse(
            u.getId(),      
            token,
            u.getEmail(),
            u.getRole()
    );
    }

}
