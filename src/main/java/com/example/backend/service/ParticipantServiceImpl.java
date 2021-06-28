package com.example.backend.service;

import com.example.backend.model.Participant;
import com.example.backend.repository.ParticipantRepository;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private static ObjectIdGenerators.UUIDGenerator uuidGenerator = new ObjectIdGenerators.UUIDGenerator();

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public void create(Participant participant) {
        UUID participantId = uuidGenerator.generateId(participant.getId());
        participant.setId(participantId);
        participantRepository.create(participant);
    }

    @Override
    public List<Participant> readAll() {
        return participantRepository.readAll();
    }

    @Override
    public Participant read(String firstName) {
        for(Participant participant : participantRepository.readAll()){
            if(participant.getFirstName().equals(firstName))
                return participant;
        }
        return null;
    }
    @Override
    public Participant readV2(String firstName, String lastName) {
        for(Participant participant : participantRepository.readAll()){
            if(participant.getFirstName().equals(firstName) && participant.getLastName().equals(lastName))
                return participant;
        }
        return null;
    }

    @Override
    public boolean update(Participant oldParticipant, Participant newParticipant) {
        return participantRepository.update(oldParticipant, newParticipant);
    }

    @Override
    public boolean delete(Participant participant) {
//        return participants.remove(participant);
        return true;
    }
}
