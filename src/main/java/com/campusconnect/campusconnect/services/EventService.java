package com.campusconnect.campusconnect.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.campusconnect.campusconnect.models.Event;
import com.campusconnect.campusconnect.repositories.EventRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event getEventById(UUID id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Event with ID " + id + " not found"));
    }
}
