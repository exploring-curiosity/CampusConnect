package com.campusconnect.campusconnect.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusconnect.campusconnect.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findById(UUID id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String Email);
    Optional<User> findByUsername(String username);
}
