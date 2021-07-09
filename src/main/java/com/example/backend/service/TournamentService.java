package com.example.backend.service;

import com.example.backend.model.Club;
import com.example.backend.model.Tournament;
import com.example.backend.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    public boolean create(Tournament tournament) {
        return tournamentRepository.create(tournament);
    }

    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    public Tournament findByTournamentId(UUID tournamentId) {
        try {
            return tournamentRepository.search(tournamentId);
        }
        catch (Exception e) { return null;}
    }

    public Tournament findByName(String name) {
        try {
            return tournamentRepository.search(name);
        }
        catch (Exception e) { return null;}
    }

    public boolean update(UUID tournamentId, Tournament newTournament) {
        if (!newTournament.equals(null))
            return tournamentRepository.update(tournamentId, newTournament);
        return false;
    }

    public boolean delete(UUID tournamentId) {
        return tournamentRepository.delete(tournamentId);
    }
}
