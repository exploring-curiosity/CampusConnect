package com.campusconnect.campusconnect.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusconnect.campusconnect.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findByIsPublicTrue();
}
