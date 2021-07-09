package com.example.backend.service;

import com.example.backend.model.Nomination;
import com.example.backend.repository.NominationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NominationService {
    @Autowired
    private NominationRepository nominationRepository;

    public boolean create(Nomination nomination) {
        return nominationRepository.create(nomination);
    }

    public List<Nomination> findAll() {
        return nominationRepository.findAll();
    }

    public Nomination findByNominationId(UUID nominationId) {
        try {
            return nominationRepository.search(nominationId);
        }
        catch (Exception e) { return null;}
    }

    public List<Nomination> findByTournamentId(UUID tournamentId) {
        try {
            return nominationRepository.searchByTournament(tournamentId);
        }
        catch (Exception e) { return null;}
    }

    public List<Nomination> findByName(String name) {
        try {
            return nominationRepository.search(name);
        }
        catch (Exception e) { return null;}
    }

    public List<Nomination> findByCategory(String category) {
        try {
            return nominationRepository.searchByCategory(category);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Nomination> findByTournamentIdAndCategory(UUID tournamentId, String category) {
        try {
            return nominationRepository.search(tournamentId, category);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean update(UUID nominationId, Nomination newNomination) {
        if (!newNomination.equals(null))
            return nominationRepository.update(nominationId, newNomination);
        return false;
    }

    public boolean delete(UUID nominationId) {
        return nominationRepository.delete(nominationId);
    }

}
