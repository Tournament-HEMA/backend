package com.example.backend.controller;

import com.example.backend.model.Duel;
import com.example.backend.service.DuelService;
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
public class DuelController {
    private final DuelService duelService;

    @Autowired
    public DuelController(DuelService duelService) {
        this.duelService = duelService;
    }

    @ApiOperation(
            value = "Добавление нового матча.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @PostMapping(value = "/user/duels")
    public ResponseEntity<?> create(
            @ApiParam(value = "Модель для добавления нового матча.", required = true)
            @RequestBody Duel duel) {
        final boolean created = duelService.create(duel);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation(
            value = "Получение всех матчей.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/duels")
    public ResponseEntity<List<Duel>> findAll() {
        final List<Duel> duels = duelService.findAll();

        return duels != null &&  !duels.isEmpty()
                ? new ResponseEntity<>(duels, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск матча по id.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/duels/findById")
    public ResponseEntity<Duel> findByDuelId(
            @ApiParam(value = "Id искомого матча.", required = true)
            @RequestParam(value = "duelId") UUID duelId)
    {
        final Duel duel = duelService.findByDuelId(duelId);

        return (duel != null)
                ? new ResponseEntity<>(duel, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск матчей по id раунда.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/duels/findByRoundId")
    public ResponseEntity<List<Duel>> findByRoundId(
            @ApiParam(value = "Id раунда, в котором будет проводиться поиск матчей.", required = true)
            @RequestParam(value = "roundId") UUID roundId)
    {
        final List<Duel> duels = duelService.findByRoundId(roundId);

        return duels != null
                ? new ResponseEntity<>(duels, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск матча по id раунда и по номеру матча.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/duels/findByRoundIdAndNumber")
    public ResponseEntity<Duel> findByRoundIdAndNumber(
            @ApiParam(value = "Id раунда, в котором будет проводиться поиск матчей.", required = true)
            @RequestParam(value = "roundId") UUID roundId,
            @ApiParam(value = "Номер искомого матча.", required = true)
            @RequestParam(value = "number") int number)
    {
        final Duel duel = duelService.findByRoundIdAndNumber(roundId, number);

        return duel != null
                ? new ResponseEntity<>(duel, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск матчей по id участника.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/duels/findByParticipantId")
    public ResponseEntity<List<Duel>> findByParticipantId(
            @ApiParam(value = "Id участника, по которому будет проводиться поиск матчей.", required = true)
            @RequestParam(value = "participantId") UUID participantId)
    {
        final List<Duel> duels = duelService.findByParticipantId(participantId);

        return duels != null
                ? new ResponseEntity<>(duels, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск матчей по id участника и id раунда.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/duels/findByParticipantIdAndRoundId")
    public ResponseEntity<List<Duel>> findByParticipantIdAndRoundId(
            @ApiParam(value = "Id участника, по которому будет проводиться поиск матчей.", required = true)
            @RequestParam(value = "participantId") UUID participantId,
            @ApiParam(value = "Id раунда, в котором будет проводиться поиск матчей.", required = true)
            @RequestParam(value = "roundId") UUID roundId)
    {
        final List<Duel> duels = duelService.findByParticipantIdAndRoundId(participantId, roundId);

        return duels != null
                ? new ResponseEntity<>(duels, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Модификация данных матча.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @PutMapping(value = "/user/duels")
    public ResponseEntity<?> update(
            @ApiParam(value = "Id матча, данные которого нужно изменить.", required = true)
            @RequestParam(value = "duelId") UUID duelId,
            @ApiParam(value = "Модель для изменения данных матча.", required = true)
            @RequestBody Duel newDuel) {
        final boolean updated = duelService.update(duelId, newDuel);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation(
            value = "Удаление матча.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @DeleteMapping(value = "/user/duels")
    public ResponseEntity<?> delete(
            @ApiParam(value = "Id удаляемого матча.", required = true)
            @RequestParam(value = "duelId") UUID duelId) {
        final boolean deleted = duelService.delete(duelId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
