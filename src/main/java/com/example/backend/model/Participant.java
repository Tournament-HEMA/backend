package com.example.backend.model;
import lombok.Data;

import java.util.UUID;

@Data
// Где private?
// И тут ты уже юзаешь @Data, lombok сгенерит для тебя getters/setters
public class Participant {
    private UUID id;
    private String firstName;
    private String lastName;
    private int number;
}
