package com.example.backend.controller;

import com.example.backend.model.Round;
import com.example.backend.service.RoundService;
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
public class RoundController {
    private final RoundService roundService;

    @Autowired
    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @ApiOperation(
            value = "Добавление нового раунда.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @PostMapping(value = "/user/rounds")
    public ResponseEntity<?> create(
            @ApiParam(value = "Модель для добавления нового раунда.", required = true)
            @RequestBody Round round) {
        final boolean created = roundService.create(round);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation(
            value = "Получение всех раундов.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/rounds")
    public ResponseEntity<List<Round>> findAll() {
        final List<Round> rounds = roundService.findAll();

        return rounds != null &&  !rounds.isEmpty()
                ? new ResponseEntity<>(rounds, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск раунда по id.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/rounds/findById")
    public ResponseEntity<Round> findByRoundId(
            @ApiParam(value = "Id искомого раунда.", required = true)
            @RequestParam(value = "roundId") UUID roundId)
    {
        final Round round = roundService.findByRoundId(roundId);

        return (round != null)
                ? new ResponseEntity<>(round, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск раунда по id номинации.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/rounds/findByNominationId")
    public ResponseEntity<List<Round>> findByNominationId(
            @ApiParam(value = "Id номинации, в которой будет проводиться поиск раундов.", required = true)
            @RequestParam(value = "nominationId") UUID nominationId)
    {
        final List<Round> rounds = roundService.findByNominationId(nominationId);

        return rounds != null
                ? new ResponseEntity<>(rounds, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск раунда по id номинации и по номеру раунда.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/rounds/findByNominationIdAndNumber")
    public ResponseEntity<Round> findByNominationIdAndNumber(
            @ApiParam(value = "Id номинации, в которой будет проводиться поиск раундов.", required = true)
            @RequestParam(value = "nominationId") UUID nominationId,
            @ApiParam(value = "Номер искомого раунда.", required = true)
            @RequestParam(value = "number") int number)
    {
        final Round round = roundService.findByNominationIdAndNumber(nominationId, number);

        return round != null
                ? new ResponseEntity<>(round, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Модификация данных раунда.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @PutMapping(value = "/user/rounds")
    public ResponseEntity<?> update(
            @ApiParam(value = "Id раунда, данные которого нужно изменить.", required = true)
            @RequestParam(value = "roundId") UUID roundId,
            @ApiParam(value = "Модель для изменения данных раунда.", required = true)
            @RequestBody Round newRound) {
        final boolean updated = roundService.update(roundId, newRound);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation(
            value = "Удаление раунда.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @DeleteMapping(value = "/user/rounds")
    public ResponseEntity<?> delete(
            @ApiParam(value = "Id удаляемого раунда.", required = true)
            @RequestParam(value = "roundId") UUID roundId) {
        final boolean deleted = roundService.delete(roundId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
