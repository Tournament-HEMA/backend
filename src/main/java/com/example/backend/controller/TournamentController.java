package com.example.backend.controller;

import com.example.backend.model.Tournament;
import com.example.backend.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TournamentController {
    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService)
    {
        this.tournamentService = tournamentService;
    }

    @PostMapping(value = "/tournaments")
    public ResponseEntity<?> create(@RequestBody Tournament tournament) {
        final boolean created = tournamentService.create(tournament);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/tournaments")
    public ResponseEntity<List<Tournament>> findAll() {
        final List<Tournament> tournaments = tournamentService.findAll();

        return tournaments != null &&  !tournaments.isEmpty()
                ? new ResponseEntity<>(tournaments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/tournaments/findById")
    public ResponseEntity<Tournament> findByTournamentId(@RequestParam(value = "tournamentId") UUID tournamentId)
    {
        final Tournament tournament = tournamentService.findByTournamentId(tournamentId);

        return (tournament != null)
                ? new ResponseEntity<>(tournament, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/tournaments/findByName")
    public ResponseEntity<Tournament> findByName(@RequestParam(value = "name") String name)
    {
        final Tournament tournament = tournamentService.findByName(name);

        return tournament != null
                ? new ResponseEntity<>(tournament, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/tournaments")
    public ResponseEntity<?> update(@RequestParam(value = "tournamentId") UUID tournamentId,
                                    @RequestBody Tournament newTournament) {
        final boolean updated = tournamentService.update(tournamentId, newTournament);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/tournaments")
    public ResponseEntity<?> delete(@RequestParam(value = "tournamentId") UUID tournamentId) {
        final boolean deleted = tournamentService.delete(tournamentId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}