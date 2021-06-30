package com.example.backend.service;

import com.example.backend.model.Participant;

import java.util.List;
import java.util.UUID;

public interface ParticipantService {

    boolean create(Participant participant);

    List<Participant> findAll();

    List<Participant> findByFirstName(String firstName);

    List<Participant> findByFirstNameAndByLastName(String firstName, String lastName);

    Participant findByFirstNameByLastNameAndByNumber(String firstName, String lastName, int number);

    Participant findByNumber(int number);

    boolean update(int oldParticipantNumber, Participant newParticipant);

    boolean delete(int number);
}
