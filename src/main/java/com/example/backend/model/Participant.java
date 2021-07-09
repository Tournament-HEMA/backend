package com.example.backend.model;
import lombok.Data;

import java.util.UUID;

@Data
public class Participant {
    private UUID hemaratingsId;
    private int number;
    private String firstName;
    private String lastName;
    private String patronymic;
    private UUID clubId;
}
