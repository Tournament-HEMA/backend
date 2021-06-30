package com.example.backend.service;

import com.example.backend.model.Participant;
import com.example.backend.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public boolean create(Participant participant) {
        return participantRepository.create(participant);
    }

    @Override
    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    @Override
    public List<Participant> findByFirstName(String firstName) {
        try {
            return participantRepository.search(firstName);
        }
        catch (Exception e) { return null;}
    }

    @Override
    public List<Participant> findByFirstNameAndByLastName(String firstName, String lastName) {
        try {
            return participantRepository.search(firstName, lastName);
        }
        catch (Exception e) { return null;}
    }

    @Override
    public Participant findByFirstNameByLastNameAndByNumber(String firstName, String lastName, int number) {
        try {
            return participantRepository.search(firstName, lastName, number);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Participant findByNumber(int number) {
        try {
            return participantRepository.search(number);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(int oldParticipantNumber, Participant newParticipant) {
        if (!newParticipant.equals(null))
            return participantRepository.update(oldParticipantNumber, newParticipant);
        return false;
    }

    @Override
    public boolean delete(int number) {
        return participantRepository.delete(number);
    }
}
