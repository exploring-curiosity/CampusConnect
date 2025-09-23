package com.campusconnect.campusconnect.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.campusconnect.campusconnect.enums.Department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event {
    private UUID id;
    private String title;
    private String description;
    private UUID organizerID;
    private LocalDateTime date;
    private String location;

    private Boolean isPublic;
    private String accessCode;
    
    private List<Department> openTo;
    
    private Integer maxAttendees;

    private Boolean isClosed = false;
    private String closingNote = "";
}
