package com.example.backend.model;
import lombok.Data;

import java.util.UUID;

@Data
public class Participant {
    UUID id;
    String firstName;
    String lastName;
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getId()
    {
        return id;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getLastName()
    {
        return lastName;
    }
}
