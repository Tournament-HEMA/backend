package com.example.backend.model;
import lombok.Data;

import java.util.UUID;

@Data
public class Participant {
    private UUID id;
    private String firstName;
    private String lastName;
    private int number;
}
