package com.example.backend.service;

import com.example.backend.model.Participant;
import com.example.backend.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public boolean create(Participant participant) {
        return participantRepository.create(participant);
    }

    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    public List<Participant> findByFirstName(String firstName) {
        try {
            return participantRepository.search(firstName);
        }
        catch (Exception e) { return null;}
    }

    public List<Participant> findByFirstNameAndByLastName(String firstName, String lastName) {
        try {
            return participantRepository.search(firstName, lastName);
        }
        catch (Exception e) { return null;}
    }

    public List<Participant> findByFirstNameByLastNameAndByPatronymic(String firstName, String lastName, String patronymic) {
        try {
            return participantRepository.search(firstName, lastName, patronymic);
        }
        catch (Exception e) { return null;}
    }

    public Participant findByFirstNameByLastNameAndByNumber(String firstName, String lastName, int number) {
        try {
            return participantRepository.search(firstName, lastName, number);
        } catch (Exception e) {
            return null;
        }
    }

    public Participant findByNumber(int number) {
        try {
            return participantRepository.search(number);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Participant> findByClubId(UUID clubId)
    {
        try {
            return participantRepository.search(clubId);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean update(int oldParticipantNumber, Participant newParticipant) {
        if (!newParticipant.equals(null))
            return participantRepository.update(oldParticipantNumber, newParticipant);
        return false;
    }

    public boolean delete(int number) {
        return participantRepository.delete(number);
    }
}
