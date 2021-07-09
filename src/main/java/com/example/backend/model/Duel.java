package com.example.backend.model;
import lombok.Data;
import lombok.Value;

import java.util.UUID;

@Data
public class Duel {
    private UUID duelId;
    private UUID roundId;
    private int number;
    private UUID firstOpponent;
    private UUID secondOpponent;
    private int firstOpponentPoints;
    private int secondOpponentPoints;
}
