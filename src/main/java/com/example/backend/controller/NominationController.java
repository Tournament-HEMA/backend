package com.example.backend.controller;

import com.example.backend.model.Nomination;
import com.example.backend.service.NominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class NominationController {
    private final NominationService nominationService;

    @Autowired
    public NominationController(NominationService nominationService) {
        this.nominationService = nominationService;
    }

    @PostMapping(value = "/user/nominations")
    public ResponseEntity<?> create(@RequestBody Nomination nomination) {
        final boolean created = nominationService.create(nomination);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/guest/nominations")
    public ResponseEntity<List<Nomination>> findAll() {
        final List<Nomination> nominations = nominationService.findAll();

        return nominations != null &&  !nominations.isEmpty()
                ? new ResponseEntity<>(nominations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/guest/nominations/findById")
    public ResponseEntity<Nomination> findByNominationId(@RequestParam(value = "nominationId") UUID nominationId)
    {
        final Nomination nomination = nominationService.findByNominationId(nominationId);

        return (nomination != null)
                ? new ResponseEntity<>(nomination, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/guest/nominations/findByTournament")
    public ResponseEntity<List<Nomination>> findByTournamentId(@RequestParam(value = "tournamentId") UUID tournamentId)
    {
        final List<Nomination> nominations = nominationService.findByTournamentId(tournamentId);

        return nominations != null
                ? new ResponseEntity<>(nominations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/guest/nominations/findByName")
    public ResponseEntity<List<Nomination>> findByName(@RequestParam(value = "name") String name)
    {
        final List<Nomination> nominations = nominationService.findByName(name);

        return nominations != null
                ? new ResponseEntity<>(nominations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/guest/nominations/findByCategory")
    public ResponseEntity<List<Nomination>> findByCategory(@RequestParam(value = "category") String category)
    {
        final List<Nomination> nominations = nominationService.findByCategory(category);

        return nominations != null
                ? new ResponseEntity<>(nominations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/guest/nominations/findByTournamentIdAndCategory")
    public ResponseEntity<List<Nomination>> findByTournamentIdAndCategory(@RequestParam(value = "tournamentId") UUID tournamentId,
                                                                          @RequestParam(value = "category") String category)
    {
        final List<Nomination> nominations = nominationService.findByTournamentIdAndCategory(tournamentId, category);

        return nominations != null
                ? new ResponseEntity<>(nominations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/user/nominations")
    public ResponseEntity<?> update(@RequestParam(value = "nominationId") UUID nominationId,
                                    @RequestBody Nomination newNomination) {
        final boolean updated = nominationService.update(nominationId, newNomination);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/user/nominations")
    public ResponseEntity<?> delete(@RequestParam(value = "nominationId") UUID nominationId) {
        final boolean deleted = nominationService.delete(nominationId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
