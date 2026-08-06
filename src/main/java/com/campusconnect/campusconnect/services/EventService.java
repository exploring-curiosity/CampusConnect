package com.campusconnect.campusconnect.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campusconnect.campusconnect.models.Event;
import com.campusconnect.campusconnect.repositories.EventRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Transactional
    public void deleteEvent(UUID id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(UUID id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Event with ID " + id + " not found"));
    }
}
