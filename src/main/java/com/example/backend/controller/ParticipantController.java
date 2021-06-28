package com.example.backend.controller;

import com.example.backend.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.service.ParticipantService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ParticipantController {
    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping(value = "/participants")
    public ResponseEntity<?> create(@RequestBody Participant participant) {
        participantService.create(participant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/participants")
    public ResponseEntity<List<Participant>> readAll() {
        final List<Participant> participants = participantService.readAll();

        return participants != null &&  !participants.isEmpty()
                ? new ResponseEntity<>(participants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/participants/read")
    public ResponseEntity<Participant> read(@RequestParam(value = "firstName") String firstName)
    {
        final Participant participant = participantService.read(firstName);

        return (participant != null)
                ? new ResponseEntity<>(participant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/participants/readv2")
    public ResponseEntity<Participant> readv2(@RequestParam(value = "firstName") String firstName,
                                            @RequestParam(value = "lastName") String lastName)
    {
        final Participant participant = participantService.readV2(firstName, lastName);

        return participant != null
                ? new ResponseEntity<>(participant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/participants")
    public ResponseEntity<?> update(@RequestParam(value = "firstName") String firstName,
                                    @RequestParam(value = "lastName") String lastName,
                                    @RequestBody Participant newParticipant) {
        Participant oldParticipant = participantService.readV2(firstName, lastName);
        final boolean updated = participantService.update(oldParticipant, newParticipant);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/participants")
    public ResponseEntity<?> delete(@RequestParam(value = "firstName") String firstName,
                                    @RequestParam(value = "lastName") String lastName) {
        Participant participant = participantService.readV2(firstName, lastName);
        final boolean deleted = participantService.delete(participant);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
