package com.example.backend.controller;

import com.example.backend.model.Tournament;
import com.example.backend.service.TournamentService;
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
public class TournamentController {
    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService)
    {
        this.tournamentService = tournamentService;
    }

    @ApiOperation(
            value = "Добавление нового турнира.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @PostMapping(value = "/user/tournaments")
    public ResponseEntity<?> create(
            @ApiParam(value = "Модель для добавления нового турнира.", required = true)
            @RequestBody Tournament tournament) {
        final boolean created = tournamentService.create(tournament);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation(
            value = "Получение всех турниров.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/tournaments")
    public ResponseEntity<List<Tournament>> findAll() {
        final List<Tournament> tournaments = tournamentService.findAll();

        return tournaments != null &&  !tournaments.isEmpty()
                ? new ResponseEntity<>(tournaments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск турнира по id.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/tournaments/findById")
    public ResponseEntity<Tournament> findByTournamentId(
            @ApiParam(value = "Id искомого турнира.", required = true)
            @RequestParam(value = "tournamentId") UUID tournamentId)
    {
        final Tournament tournament = tournamentService.findByTournamentId(tournamentId);

        return (tournament != null)
                ? new ResponseEntity<>(tournament, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск турнира по названию.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/tournaments/findByName")
    public ResponseEntity<Tournament> findByName(
            @ApiParam(value = "Название искомого турнира.", required = true)
            @RequestParam(value = "name") String name)
    {
        final Tournament tournament = tournamentService.findByName(name);

        return tournament != null
                ? new ResponseEntity<>(tournament, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Модификация данных турнира.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @PutMapping(value = "/user/tournaments")
    public ResponseEntity<?> update(
            @ApiParam(value = "Id турнира, данные которого нужно изменить.", required = true)
            @RequestParam(value = "tournamentId") UUID tournamentId,
            @ApiParam(value = "Модель для изменения данных турнира.", required = true)
            @RequestBody Tournament newTournament) {
        final boolean updated = tournamentService.update(tournamentId, newTournament);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation(
            value = "Удаление турнира.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @DeleteMapping(value = "/user/tournaments")
    public ResponseEntity<?> delete(
            @ApiParam(value = "Id удаляемого турнира.", required = true)
            @RequestParam(value = "tournamentId") UUID tournamentId) {
        final boolean deleted = tournamentService.delete(tournamentId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}