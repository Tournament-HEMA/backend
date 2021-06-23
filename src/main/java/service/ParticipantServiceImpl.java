package service;

import model.Participant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    private static Map<Integer, Participant> PARTICIPANT_Repository_Map = new HashMap<>();

//    private static AtomicInteger PARTICIPANT_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Participant participant) {
//        int ParticipantId = PARTICIPANT_ID_HOLDER.incrementAndGet();
//        participant.setId(ParticipantId);
        PARTICIPANT_Repository_Map.put(participant.getId(), participant);
    }

    @Override
    public List<Participant> readAll() {
        return new ArrayList<>(PARTICIPANT_Repository_Map.values());
    }

    @Override
    public Participant read(int id) {
        return PARTICIPANT_Repository_Map.get(id);
    }

    @Override
    public boolean update(Participant participant, int id) {
        if (PARTICIPANT_Repository_Map.containsKey(id)) {
            PARTICIPANT_Repository_Map.put(id, new Participant(id, participant.getName()));
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return PARTICIPANT_Repository_Map.remove(id) != null;
    }
}
