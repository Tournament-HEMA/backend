package com.example.backend.model;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRole {
    private UUID id;
    private UUID userId;
    private UUID roleId;
}
