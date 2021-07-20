package com.example.backend.controller;

import com.example.backend.model.Participant;
import com.example.backend.service.ParticipantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(
            value = "Добавление нового участника.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @PostMapping(value = "/user/participants")
    public ResponseEntity<?> create(
            @ApiParam(value = "Модель для добавления нового участника.", required = true)
            @RequestBody Participant participant) {
        final boolean created = participantService.create(participant);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation(
            value = "Получение всех участников.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/participants")
    public ResponseEntity<List<Participant>> findAll() {
        final List<Participant> participants = participantService.findAll();

        return participants != null &&  !participants.isEmpty()
                ? new ResponseEntity<>(participants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск участников по имени.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/participants/findByName")
    public ResponseEntity<List<Participant>> findByFirstName(
            @ApiParam(value = "Имя участника.", required = true)
            @RequestParam(value = "firstName") String firstName)
    {
        final List<Participant> participants = participantService.findByFirstName(firstName);

        return (participants != null)
                ? new ResponseEntity<>(participants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск участников по имени и фамилии.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/participants/findByNameSurname")
    public ResponseEntity<List<Participant>> findByFirstNameAndByLastName(
            @ApiParam(value = "Имя участника.", required = true)
            @RequestParam(value = "firstName") String firstName,
            @ApiParam(value = "Фамилия участника.", required = true)
            @RequestParam(value = "lastName") String lastName)
    {
        final List<Participant> participants = participantService.findByFirstNameAndByLastName(firstName, lastName);

        return participants != null
                ? new ResponseEntity<>(participants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск участников по имени, фамилии и отчеству.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/participants/findByNameSurnamePatronymic")
    public ResponseEntity<List<Participant>> findByFirstNameByLastNameAndByPatronymic(
            @ApiParam(value = "Имя участника.", required = true)
            @RequestParam(value = "firstName") String firstName,
            @ApiParam(value = "Фамилия участника.", required = true)
            @RequestParam(value = "lastName") String lastName,
            @ApiParam(value = "Отчество участника.", required = true)
            @RequestParam(value = "patronymic") String patronymic)
    {
        final List<Participant> participants = participantService.findByFirstNameByLastNameAndByPatronymic(firstName, lastName, patronymic);

        return participants != null
                ? new ResponseEntity<>(participants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск участника по имени, фамилии и номеру.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/participants/findByNameSurnameNumber")
    public ResponseEntity<Participant> findByFirstNameByLastNameAndByNumber(
            @ApiParam(value = "Имя участника.", required = true)
            @RequestParam(value = "firstName") String firstName,
            @ApiParam(value = "Фамилия участника.", required = true)
            @RequestParam(value = "lastName") String lastName,
            @ApiParam(value = "Номер участника.", required = true)
            @RequestParam(value = "number") int number)
    {
        final Participant participant = participantService.findByFirstNameByLastNameAndByNumber(firstName, lastName, number);

        return participant != null
                ? new ResponseEntity<>(participant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск участника по номеру.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/participants/findByNumber")
    public ResponseEntity<Participant> findByNumber(
            @ApiParam(value = "Номер участника.", required = true)
            @RequestParam(value = "number") int number)
    {
        final Participant participant = participantService.findByNumber(number);

        return participant != null
                ? new ResponseEntity<>(participant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск участников по id клуба, в котором они состоят.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/participants/findByClubId")
    public ResponseEntity<List<Participant>> findByClubId(
            @ApiParam(value = "Id клуба, по которому проходит поиск участников.", required = true)
            @RequestParam(value = "clubId") UUID clubId)
    {
        final List<Participant> participants = participantService.findByClubId(clubId);

        return participants != null
                ? new ResponseEntity<>(participants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Модификация данных участника.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @PutMapping(value = "/user/participants")
    public ResponseEntity<?> update(
            @ApiParam(value = "Номер модифицируемого участника.", required = true)
            @RequestParam(value = "number") int number,
            @ApiParam(value = "Модель для изменения данных участника.", required = true)
            @RequestBody Participant newParticipant) {
        final boolean updated = participantService.update(number, newParticipant);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation(
            value = "Удаление участника.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @DeleteMapping(value = "/user/participants")
    public ResponseEntity<?> delete(
            @ApiParam(value = "Номер удаляемого участника.", required = true)
            @RequestParam(value = "number") int number) {
        final boolean deleted = participantService.delete(number);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
