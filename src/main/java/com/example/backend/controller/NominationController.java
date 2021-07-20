package com.example.backend.controller;

import com.example.backend.model.Nomination;
import com.example.backend.service.NominationService;
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
public class NominationController {
    private final NominationService nominationService;

    @Autowired
    public NominationController(NominationService nominationService) {
        this.nominationService = nominationService;
    }

    @ApiOperation(
            value = "Добавление новой номинации.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @PostMapping(value = "/user/nominations")
    public ResponseEntity<?> create(
            @ApiParam(value = "Модель для добавления новой номинации.", required = true)
            @RequestBody Nomination nomination) {
        final boolean created = nominationService.create(nomination);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation(
            value = "Получение всех номинаций.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/nominations")
    public ResponseEntity<List<Nomination>> findAll() {
        final List<Nomination> nominations = nominationService.findAll();

        return nominations != null &&  !nominations.isEmpty()
                ? new ResponseEntity<>(nominations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск номинации по id.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/nominations/findById")
    public ResponseEntity<Nomination> findByNominationId(
            @ApiParam(value = "Id искомой номинации.", required = true)
            @RequestParam(value = "nominationId") UUID nominationId)
    {
        final Nomination nomination = nominationService.findByNominationId(nominationId);

        return (nomination != null)
                ? new ResponseEntity<>(nomination, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск номинаций по id турнира.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/nominations/findByTournament")
    public ResponseEntity<List<Nomination>> findByTournamentId(
            @ApiParam(value = "Id турнира, в котором будет проводиться поиск номинаций.", required = true)
            @RequestParam(value = "tournamentId") UUID tournamentId)
    {
        final List<Nomination> nominations = nominationService.findByTournamentId(tournamentId);

        return nominations != null
                ? new ResponseEntity<>(nominations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск номинаций по названию.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/nominations/findByName")
    public ResponseEntity<List<Nomination>> findByName(
            @ApiParam(value = "Название номинации.", required = true)
            @RequestParam(value = "name") String name)
    {
        final List<Nomination> nominations = nominationService.findByName(name);

        return nominations != null
                ? new ResponseEntity<>(nominations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск номинаций по категории.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/nominations/findByCategory")
    public ResponseEntity<List<Nomination>> findByCategory(
            @ApiParam(value = "Название категории.", required = true)
            @RequestParam(value = "category") String category)
    {
        final List<Nomination> nominations = nominationService.findByCategory(category);

        return nominations != null
                ? new ResponseEntity<>(nominations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск номинаций id турнира и по категории.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/nominations/findByTournamentIdAndCategory")
    public ResponseEntity<List<Nomination>> findByTournamentIdAndCategory(
            @ApiParam(value = "Id турнира, в котором будет проводиться поиск номинаций.", required = true)
            @RequestParam(value = "tournamentId") UUID tournamentId,
            @ApiParam(value = "Название категории.", required = true)
            @RequestParam(value = "category") String category)
    {
        final List<Nomination> nominations = nominationService.findByTournamentIdAndCategory(tournamentId, category);

        return nominations != null
                ? new ResponseEntity<>(nominations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Модификация данных номинации.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @PutMapping(value = "/user/nominations")
    public ResponseEntity<?> update(
            @ApiParam(value = "Id номинации, данные которой нужно изменить.", required = true)
            @RequestParam(value = "nominationId") UUID nominationId,
            @ApiParam(value = "Модель для изменения данных номинации.", required = true)
            @RequestBody Nomination newNomination) {
        final boolean updated = nominationService.update(nominationId, newNomination);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation(
            value = "Удаление номинации.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @DeleteMapping(value = "/user/nominations")
    public ResponseEntity<?> delete(
            @ApiParam(value = "Id удаляемой номинации.", required = true)
            @RequestParam(value = "nominationId") UUID nominationId) {
        final boolean deleted = nominationService.delete(nominationId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
