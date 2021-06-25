package com.example.backend.service;

import com.example.backend.model.Participant;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    private static List<Participant> PARTICIPANT_Repository_List = new ArrayList<Participant>();

    private static ObjectIdGenerators.UUIDGenerator uuidGenerator = new ObjectIdGenerators.UUIDGenerator();

    @Override
    public void create(Participant participant) {
        UUID participantId = uuidGenerator.generateId(participant.getId());
        participant.setId(participantId);
        PARTICIPANT_Repository_List.add(participant);
    }

    @Override
    public List<Participant> readAll() {
        return PARTICIPANT_Repository_List;
    }

    @Override
    public Participant read(String firstName) {
        for(Participant participant : PARTICIPANT_Repository_List){
            if(participant.getFirstName().equals(firstName))
                return participant;
        }
        return null;
    }
    @Override
    public Participant readv2(String firstName, String lastName) {
        for(Participant participant : PARTICIPANT_Repository_List){
            if(participant.getFirstName().equals(firstName) && participant.getLastName().equals(lastName))
                return participant;
        }
        return null;
    }

    @Override
    public boolean update(Participant oldParticipant, Participant newParticipant) {
        if (PARTICIPANT_Repository_List.contains(oldParticipant)) {
            newParticipant.setId(oldParticipant.getId());
            PARTICIPANT_Repository_List.remove(oldParticipant);
            PARTICIPANT_Repository_List.add(newParticipant);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Participant participant) {
        return PARTICIPANT_Repository_List.remove(participant);
    }
}
