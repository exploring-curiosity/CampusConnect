package com.campusconnect.campusconnect.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.campusconnect.config.JwtUtil;
import com.campusconnect.campusconnect.models.User;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    
    private List<User> users = new ArrayList<>();
    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    
    @PostMapping("/register") 
    public String register(@RequestBody User user) {
        UUID userId = UUID.randomUUID();
        user.setId(userId);
        users.add(user);
        return "User Added Successfully, "+ user.getUsername();
    }

    @PostMapping("/login") 
    public String login(@RequestBody User user){
        for (User u: users) {
            if(u.getUsername().equals(user.getUsername())&& 
                u.getPassword().equals(user.getPassword())) {

                    String token = jwtUtil.generateToken(u.getUsername(), u.getRole());
                    return token;
                }
        }
        return "Invalid Credential";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('FACULTY')") 
    public List<User> getUsers() {
        return users;
    } 
}