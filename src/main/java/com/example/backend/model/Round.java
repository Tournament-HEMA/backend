package com.example.backend.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Round {
    private UUID roundId;
    private UUID nominationId;
    private int number;
    private String name;
}
