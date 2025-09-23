package com.campusconnect.campusconnect.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.campusconnect.models.Event;

@RestController
@RequestMapping("api/events")
public class EventController {
    private final Map<UUID, Event> events= new HashMap<>();

    @GetMapping("/{id}") 
    public Event getEventById(@PathVariable UUID id) {
        if ( events.containsKey(id)) {
            return events.get(id);
        }
        return null;
    }

    @PostMapping("/create") 
    public void createEvent(@RequestBody Event event) {
        UUID eventId = UUID.randomUUID();
        event.setId(eventId);
        events.put(eventId, event);
    }

    @GetMapping("/all") 
    public Collection<Event> getAllEvents() {
        return events.values();
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable UUID id, @RequestBody Event updatedEvent) {
        Event exiting = events.get(id);
        if (exiting!=null) {
            updatedEvent.setId(id);
            events.put(id, updatedEvent);
            return updatedEvent;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('STUDENT')") 
    public String deleteEvent(@PathVariable UUID id) {
        if (events.containsKey(id)) {
            events.remove(id);
            return "Event Deleted Successfully";
        }
        return "Event Not Found";
    }
    
    @PutMapping("/{id}/close")
    public String closeEvent(@PathVariable UUID id, @RequestBody Map<String, String> body) {
        Event event = events.get(id);
        if (event != null) {
            String note = body.get("note");
            if( note == null || note.isEmpty()) {
                return "Closing note is required";
            }
            event.setIsClosed(true);
            event.setClosingNote(note);
            events.put(id, event);
            return "Event closed";
        }
        return "Event Not Found";
    }
}
