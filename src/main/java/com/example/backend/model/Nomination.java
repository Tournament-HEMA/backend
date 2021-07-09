package com.example.backend.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Nomination {
    private UUID nominationId;
    private UUID tournamentId;
    private String category;
    private String name;
}
