package com.example.backend.controller;

import com.example.backend.model.Duel;
import com.example.backend.service.DuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class DuelController {
    private final DuelService duelService;

    @Autowired
    public DuelController(DuelService duelService) {
        this.duelService = duelService;
    }

    @PostMapping(value = "/duels")
    public ResponseEntity<?> create(@RequestBody Duel duel) {
        final boolean created = duelService.create(duel);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/duels")
    public ResponseEntity<List<Duel>> findAll() {
        final List<Duel> duels = duelService.findAll();

        return duels != null &&  !duels.isEmpty()
                ? new ResponseEntity<>(duels, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/duels/findById")
    public ResponseEntity<Duel> findByDuelId(@RequestParam(value = "duelId") UUID duelId)
    {
        final Duel duel = duelService.findByDuelId(duelId);

        return (duel != null)
                ? new ResponseEntity<>(duel, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/duels/findByRoundId")
    public ResponseEntity<List<Duel>> findByRoundId(@RequestParam(value = "roundId") UUID roundId)
    {
        final List<Duel> duels = duelService.findByRoundId(roundId);

        return duels != null
                ? new ResponseEntity<>(duels, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/duels/findByRoundIdAndNumber")
    public ResponseEntity<Duel> findByRoundIdAndNumber(@RequestParam(value = "roundId") UUID roundId,
                                                       @RequestParam(value = "number") int number)
    {
        final Duel duel = duelService.findByRoundIdAndNumber(roundId, number);

        return duel != null
                ? new ResponseEntity<>(duel, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/duels/findByParticipantId")
    public ResponseEntity<List<Duel>> findByParticipantId(@RequestParam(value = "participantId") UUID participantId)
    {
        final List<Duel> duels = duelService.findByParticipantId(participantId);

        return duels != null
                ? new ResponseEntity<>(duels, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/duels/findByParticipantIdAndRoundId")
    public ResponseEntity<List<Duel>> findByParticipantIdAndRoundId(@RequestParam(value = "participantId") UUID participantId,
                                                                    @RequestParam(value = "roundId") UUID roundId)
    {
        final List<Duel> duels = duelService.findByParticipantIdAndRoundId(participantId, roundId);

        return duels != null
                ? new ResponseEntity<>(duels, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/duels")
    public ResponseEntity<?> update(@RequestParam(value = "duelId") UUID duelId,
                                    @RequestBody Duel newDuel) {
        final boolean updated = duelService.update(duelId, newDuel);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/duels")
    public ResponseEntity<?> delete(@RequestParam(value = "duelId") UUID duelId) {
        final boolean deleted = duelService.delete(duelId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
