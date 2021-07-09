package com.example.backend.service;

import com.example.backend.model.Club;
import com.example.backend.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClubService {
    @Autowired
    private ClubRepository clubRepository;

    public boolean create(Club club) {
        return clubRepository.create(club);
    }

    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    public Club findByClubId(UUID clubId) {
        try {
            return clubRepository.search(clubId);
        }
        catch (Exception e) { return null;}
    }

    public Club findByClubname(String clubname) {
        try {
            return clubRepository.search(clubname);
        }
        catch (Exception e) { return null;}
    }

    public List<Club> findByActive(boolean active) {
        try {
            return clubRepository.search(active);
        }
        catch (Exception e) { return null;}
    }

    public List<Club> findByCity(String city) {
        try {
            return clubRepository.searchByCity(city);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean update(UUID oldClubId, Club newClub) {
        if (!newClub.equals(null))
            return clubRepository.update(oldClubId, newClub);
        return false;
    }

    public boolean delete(UUID clubId) {
        return clubRepository.delete(clubId);
    }
}
