package com.example.backend.service;

import com.example.backend.model.Participant;

import java.util.List;
import java.util.UUID;

public interface ParticipantService {

    void create(Participant participant);

    List<Participant> readAll();

    Participant read(String firstName);

    Participant readv2(String firstName, String lastName);

    boolean update(Participant oldParticipant, Participant newParticipant);

    boolean delete(Participant participant);
}
