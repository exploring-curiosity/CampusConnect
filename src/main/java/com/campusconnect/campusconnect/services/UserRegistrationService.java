package com.campusconnect.campusconnect.services;

import com.campusconnect.campusconnect.exceptions.RegistrationException;
import com.campusconnect.campusconnect.models.User;
import com.campusconnect.campusconnect.repositories.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    private final UserRepository userRepository;

    public UserRegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        try {
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new RegistrationException("Username already taken: " + user.getUsername());
            }
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new RegistrationException("Account already exists for email: " + user.getEmail());
            }
            return userRepository.save(user);
        } catch (RegistrationException e) {
            throw e;
        } catch (Exception e) {
            throw new RegistrationException("Registration failed: " + e.getMessage(), e);
        }
    }
}
