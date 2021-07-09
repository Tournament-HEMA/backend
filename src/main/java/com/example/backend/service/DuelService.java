package com.example.backend.service;

import com.example.backend.model.Duel;
import com.example.backend.repository.DuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DuelService {
    @Autowired
    private DuelRepository duelRepository;

    public boolean create(Duel duel) {
        return duelRepository.create(duel);
    }

    public List<Duel> findAll() {
        return duelRepository.findAll();
    }

    public Duel findByDuelId(UUID duelId) {
        try {
            return duelRepository.search(duelId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Duel> findByRoundId(UUID roundId) {
        try {
            return duelRepository.searchByRoundId(roundId);
        } catch (Exception e) {
            return null;
        }
    }

    public Duel findByRoundIdAndNumber(UUID roundId, int number) {
        try {
            return duelRepository.search(roundId, number);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Duel> findByParticipantId(UUID participantId) {
        try {
            return duelRepository.searchDuelsOfParticipant(participantId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Duel> findByParticipantIdAndRoundId(UUID participantId, UUID roundId) {
        try {
            return duelRepository.searchDuelsOfParticipant(participantId, roundId);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean update(UUID duelId, Duel newDuel) {
        if (!newDuel.equals(null)){
            return duelRepository.update(duelId, newDuel);
        }
        return false;
    }

    public boolean delete(UUID duelId) {
        return duelRepository.delete(duelId);
    }
}
