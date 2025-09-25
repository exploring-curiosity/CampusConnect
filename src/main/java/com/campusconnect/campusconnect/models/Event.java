package com.campusconnect.campusconnect.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.campusconnect.campusconnect.enums.Department;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "events")
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(nullable = false)
    private String title;

    private String description;
    
    @Column(nullable = false)
    private UUID organizerId;
    
    @Column(nullable = false)
    private LocalDateTime date;
    
    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Boolean isPublic;
    
    private String accessCode;
    
    @Enumerated(EnumType.STRING)
    private List<Department> openTo;
    
    private Integer maxAttendees;

    private Boolean isClosed = false;
    private String closingNote = "";

    @ElementCollection
    @Column(name = "user_id")
    private List<UUID> attendees = new ArrayList<>();
}
