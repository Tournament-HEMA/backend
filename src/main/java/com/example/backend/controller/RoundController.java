package com.example.backend.controller;

import com.example.backend.model.Round;
import com.example.backend.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class RoundController {
    private final RoundService roundService;

    @Autowired
    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @PostMapping(value = "/rounds")
    public ResponseEntity<?> create(@RequestBody Round round) {
        final boolean created = roundService.create(round);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/rounds")
    public ResponseEntity<List<Round>> findAll() {
        final List<Round> rounds = roundService.findAll();

        return rounds != null &&  !rounds.isEmpty()
                ? new ResponseEntity<>(rounds, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/rounds/findById")
    public ResponseEntity<Round> findByRoundId(@RequestParam(value = "roundId") UUID roundId)
    {
        final Round round = roundService.findByRoundId(roundId);

        return (round != null)
                ? new ResponseEntity<>(round, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/rounds/findByNominationId")
    public ResponseEntity<List<Round>> findByNominationId(@RequestParam(value = "nominationId") UUID nominationId)
    {
        final List<Round> rounds = roundService.findByNominationId(nominationId);

        return rounds != null
                ? new ResponseEntity<>(rounds, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/rounds/findByNominationIdAndNumber")
    public ResponseEntity<Round> findByNominationIdAndNumber(@RequestParam(value = "nominationId") UUID nominationId,
                                                             @RequestParam(value = "number") int number)
    {
        final Round round = roundService.findByNominationIdAndNumber(nominationId, number);

        return round != null
                ? new ResponseEntity<>(round, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/rounds")
    public ResponseEntity<?> update(@RequestParam(value = "roundId") UUID roundId,
                                    @RequestBody Round newRound) {
        final boolean updated = roundService.update(roundId, newRound);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/rounds")
    public ResponseEntity<?> delete(@RequestParam(value = "roundId") UUID roundId) {
        final boolean deleted = roundService.delete(roundId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
