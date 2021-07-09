package com.example.backend.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Club {
    private UUID clubId;
    private String clubname;
    private String city;
    private boolean active;
}
