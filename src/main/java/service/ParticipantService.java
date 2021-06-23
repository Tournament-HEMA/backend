package service;

import model.Participant;

import java.util.List;

public interface ParticipantService {

    void create(Participant participant);

    List<Participant> readAll();

    Participant read(int id);

    boolean update(Participant participant, int id);

    boolean delete(int id);
}
