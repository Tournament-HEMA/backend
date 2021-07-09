package com.example.backend.service;

import com.example.backend.model.Round;
import com.example.backend.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoundService {
    @Autowired
    private RoundRepository roundRepository;

    public boolean create(Round round) {
        return roundRepository.create(round);
    }

    public List<Round> findAll() {
        return roundRepository.findAll();
    }

    public Round findByRoundId(UUID roundId) {
        try {
            return roundRepository.search(roundId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Round> findByNominationId(UUID nominationId) {
        try {
            return roundRepository.searchByNomination(nominationId);
        } catch (Exception e) {
            return null;
        }
    }

    public Round findByNominationIdAndNumber(UUID nominationId, int number) {
        try {
            return roundRepository.search(nominationId, number);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean update(UUID roundId, Round newRound) {
        if (!newRound.equals(null)){
            return roundRepository.update(roundId, newRound);
        }
        return false;
    }

    public boolean delete(UUID roundId) {
        return roundRepository.delete(roundId);
    }
}
