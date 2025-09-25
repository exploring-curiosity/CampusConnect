package com.campusconnect.campusconnect.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.campusconnect.config.JwtUtil;
import com.campusconnect.campusconnect.models.User;
import com.campusconnect.campusconnect.repositories.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
    
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    
    @PostMapping("/register") 
    public ResponseEntity<?> register(@RequestBody User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Account exist for the given email");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login") 
    public ResponseEntity<?> login(@RequestBody Map<String, String> body){
        String username = body.get("username");
        String password = body.get("password");

        return userRepository.findByUsername(username)
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .map(u -> {
                    String token = jwtUtil.generateToken(u.getUsername(), u.getRole());
                    return ResponseEntity.ok(Map.of("token", token));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid Credentials")));
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('FACULTY')") 
    public List<User> getUsers() {
        return userRepository.findAll();
    } 
}