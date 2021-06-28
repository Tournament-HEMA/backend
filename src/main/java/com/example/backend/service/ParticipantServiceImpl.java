package com.example.backend.service;

import com.example.backend.model.Participant;
import com.example.backend.repository.ParticipantRepository;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

// В целом, в классе нет проверок на ошибки, он тебе будет стек трейсы на фронт слать
@Service
public class ParticipantServiceImpl implements ParticipantService {

    // Уже показывал как использовать UUID.randomUUID(), это тут не нужно
    private static ObjectIdGenerators.UUIDGenerator uuidGenerator = new ObjectIdGenerators.UUIDGenerator();

    @Autowired
    private ParticipantRepository participantRepository;

    // Что будет, если фихтун с такой фамилией и именем существует?
    @Override
    public void create(Participant participant) {
        UUID participantId = uuidGenerator.generateId(participant.getId());
        participant.setId(participantId); // Почему бы не передать в нутрь метода сразу?
        participantRepository.create(participant);
    }

    @Override
    public List<Participant> readAll() {
        return participantRepository.readAll();
    }

    /* Метод read, а выполняет поиск по фильтру firstName. Вообще работа базы данных */
    @Override
    public Participant read(String firstName) {
        for(Participant participant : participantRepository.readAll()){
            if(participant.getFirstName().equals(firstName))
                return participant;
        }
        return null;
    }

    // Тоже очень странный метод. Если у тебя различаются параметры, зачем называть ...V2?
    @Override
    public Participant readV2(String firstName, String lastName) {
        for(Participant participant : participantRepository.readAll()){
            if(participant.getFirstName().equals(firstName) && participant.getLastName().equals(lastName))
                return participant;
        }
        return null;
    }

    /* Вообще, проверку на null в newParticipant я бы вынес сюда */
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
