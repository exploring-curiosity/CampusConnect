package com.campusconnect.campusconnect.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
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
import com.campusconnect.campusconnect.repositories.EventRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/events")
@AllArgsConstructor
public class EventController {

    private final EventRepository eventRepository;

    @GetMapping("/{id}") 
    public ResponseEntity<?> getEventById(@PathVariable UUID id) {
        return eventRepository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create") 
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventRepository.save(event));    
    }

    @GetMapping("/all") 
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable UUID id, @RequestBody Event updatedEvent) {
        return eventRepository.findById(id)
                .map(e -> {
                    updatedEvent.setId(e.getId());
                    return ResponseEntity.ok(eventRepository.save(updatedEvent));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('STUDENT')") 
    public ResponseEntity<?> deleteEvent(@PathVariable UUID id) {
        if(eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return ResponseEntity.ok("Event Deleted Successfully");
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}/close")
    public ResponseEntity<?> closeEvent(@PathVariable UUID id, @RequestBody Map<String, String> body) {
        String note = body.get("note");
        if (note == null || note.isEmpty()) {
            return ResponseEntity.badRequest().body("A closing note is required");
        }
        // event.isClosed -> true
        // event.closingNote = note
        return eventRepository.findById(id)
                .map(e -> {
                    e.setIsClosed(true);
                    e.setClosingNote(note);
                    return ResponseEntity.ok(eventRepository.save(e));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{eventId}/attendess")
    public ResponseEntity<?> getAttendees(@PathVariable UUID eventId) {
        return eventRepository.findById(eventId)
                .map(event -> ResponseEntity.ok(event.getAttendees()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{eventId}/rsvp/{userId}")
    public ResponseEntity<?> rsvpToEvent(@PathVariable UUID eventId, @PathVariable UUID userId) {
        return eventRepository.findById(eventId)
                .map(e -> {
                    if(e.getIsClosed()) {
                        return ResponseEntity.badRequest().body("Event is Closed");
                    }
                    if(e.getAttendees().contains(userId)) {
                        return ResponseEntity.badRequest().body("User already registered");
                    }
                    if(e.getMaxAttendees() != null && e.getAttendees().size() >= e.getMaxAttendees()) {
                        return ResponseEntity.badRequest().body("Event is Full");
                    }

                    e.getAttendees().add(userId);
                    eventRepository.save(e);
                    return ResponseEntity.ok("RsVP Successfully");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{eventId}/rsvp/{userId}")
    public ResponseEntity<?> deleteRsvp(@PathVariable UUID eventId, @PathVariable UUID userId) {
        return eventRepository.findById(eventId)
                .map(e ->{
                    if(e.getAttendees().contains(userId)) {
                        e.getAttendees().remove(userId);
                        eventRepository.save(e);
                        return ResponseEntity.ok("User removed RSVP");
                    } else {
                        return ResponseEntity.badRequest().body("User not RsVPed");
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
