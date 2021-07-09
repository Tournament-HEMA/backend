package com.example.backend.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Tournament {
    private UUID tournamentId;
    private String name;
    private Date beginning;
    private Date ending;
}
