package com.campusconnect.campusconnect.models;

import java.util.UUID;


import com.campusconnect.campusconnect.enums.Department;
import com.campusconnect.campusconnect.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private Department department;
    private Role role;
    private String batch;
}
