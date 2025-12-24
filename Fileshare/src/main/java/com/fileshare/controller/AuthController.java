package com.fileshare.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fileshare.dto.LoginRequest;
import com.fileshare.entity.User;
import com.fileshare.security.JwtUtil;
import com.fileshare.store.InMemoryStore;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final InMemoryStore store;

    public AuthController(InMemoryStore store) {
        this.store = store;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {

        User user = store.users.get(req.getUsername());

        if (user == null || !user.getPassword().equals(req.getPassword())) {
            return "INVALID_CREDENTIALS";
        }

        return JwtUtil.generateToken(user.getUsername());
    }
}
