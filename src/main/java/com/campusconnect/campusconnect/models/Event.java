package com.campusconnect.campusconnect.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.campusconnect.campusconnect.enums.Department;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    
    @NotBlank
    @Column(nullable = false)
    private String title;

    private String description;
    
    @Column(nullable = false)
    private UUID organizerId;
    
    @NotNull
    @Column(nullable = false)
    private LocalDateTime date;
    
    @NotBlank
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

    @JoinTable(
        name = "event_attendees",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UUID> attendees = new ArrayList<>();
}
