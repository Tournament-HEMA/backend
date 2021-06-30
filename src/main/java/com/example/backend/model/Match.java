package com.example.backend.model;
import lombok.Data;
import lombok.Value;

@Data
public class Match {
    int id;
    int participantId;
    int score;
}
