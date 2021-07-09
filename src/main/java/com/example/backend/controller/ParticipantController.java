package com.example.backend.controller;

import com.example.backend.model.Participant;
import com.example.backend.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        final boolean created = participantService.create(participant);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/participants")
    public ResponseEntity<List<Participant>> findAll() {
        final List<Participant> participants = participantService.findAll();

        return participants != null &&  !participants.isEmpty()
                ? new ResponseEntity<>(participants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/participants/findByName")
    public ResponseEntity<List<Participant>> findByFirstName(@RequestParam(value = "firstName") String firstName)
    {
        final List<Participant> participants = participantService.findByFirstName(firstName);

        return (participants != null)
                ? new ResponseEntity<>(participants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/participants/findByNameSurname")
    public ResponseEntity<List<Participant>> findByFirstNameAndByLastName(@RequestParam(value = "firstName") String firstName,
                                                                          @RequestParam(value = "lastName") String lastName)
    {
        final List<Participant> participants = participantService.findByFirstNameAndByLastName(firstName, lastName);

        return participants != null
                ? new ResponseEntity<>(participants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/participants/findByNameSurnamePatronymic")
    public ResponseEntity<List<Participant>> findByFirstNameByLastNameAndByPatronymic(@RequestParam(value = "firstName") String firstName,
                                                                                      @RequestParam(value = "lastName") String lastName,
                                                                                      @RequestParam(value = "patronymic") String patronymic)
    {
        final List<Participant> participants = participantService.findByFirstNameByLastNameAndByPatronymic(firstName, lastName, patronymic);

        return participants != null
                ? new ResponseEntity<>(participants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/participants/findByNameSurnameNumber")
    public ResponseEntity<Participant> findByFirstNameByLastNameAndByNumber(@RequestParam(value = "firstName") String firstName,
                                                                            @RequestParam(value = "lastName") String lastName,
                                                                            @RequestParam(value = "number") int number)
    {
        final Participant participant = participantService.findByFirstNameByLastNameAndByNumber(firstName, lastName, number);

        return participant != null
                ? new ResponseEntity<>(participant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/participants/findByNumber")
    public ResponseEntity<Participant> findByNumber(@RequestParam(value = "number") int number)
    {
        final Participant participant = participantService.findByNumber(number);

        return participant != null
                ? new ResponseEntity<>(participant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/participants/findByClubId")
    public ResponseEntity<List<Participant>> findByClubId(@RequestParam(value = "clubId") UUID clubId)
    {
        final List<Participant> participants = participantService.findByClubId(clubId);

        return participants != null
                ? new ResponseEntity<>(participants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/participants")
    public ResponseEntity<?> update(@RequestParam(value = "number") int number,
                                    @RequestBody Participant newParticipant) {
        final boolean updated = participantService.update(number, newParticipant);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/participants")
    public ResponseEntity<?> delete(@RequestParam(value = "number") int number) {
        final boolean deleted = participantService.delete(number);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
