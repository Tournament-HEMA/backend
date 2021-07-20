package com.example.backend.controller;

import com.example.backend.model.Club;
import com.example.backend.service.ClubService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ClubController {
    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @ApiOperation(
            value = "Добавление нового клуба.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @PostMapping(value = "/user/clubs")
    public ResponseEntity<?> create(
            @ApiParam(value = "Модель для добавления нового клуба.", required = true)
            @RequestBody Club club) {
        final boolean created = clubService.create(club);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation(
            value = "Получение всех клубов.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/clubs")
    public ResponseEntity<List<Club>> findAll() {
        final List<Club> clubs = clubService.findAll();

        return clubs != null &&  !clubs.isEmpty()
                ? new ResponseEntity<>(clubs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск клуба по id.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/clubs/findById")
    public ResponseEntity<Club> findByClubId(
            @ApiParam(value = "Id искомого клуба.", required = true)
            @RequestParam(value = "clubId") UUID clubId)
    {
        final Club club = clubService.findByClubId(clubId);

        return (club != null)
                ? new ResponseEntity<>(club, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск клуба по его названию.",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/clubs/findByClubname")
    public ResponseEntity<Club> findByClubname(
            @ApiParam(value = "Название искомого клуба.", required = true)
            @RequestParam(value = "clubname") String clubname)
    {
        final Club club = clubService.findByClubname(clubname);

        return club != null
                ? new ResponseEntity<>(club, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск клуба по его активности(активен/неактивен).",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/clubs/findByActive")
    public ResponseEntity<List<Club>> findByActive(
            @ApiParam(value = "Параметр активен/неактивен.", required = true)
            @RequestParam(value = "active") boolean active)
    {
        final List<Club> clubs = clubService.findByActive(active);

        return clubs != null
                ? new ResponseEntity<>(clubs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Поиск клуба по его местоположению(город).",
            notes = "Доступ имеют все пользователи.")
    @GetMapping(value = "/guest/clubs/findByCity")
    public ResponseEntity<List<Club>> findByCity(
            @ApiParam(value = "Название города.", required = true)
            @RequestParam(value = "city") String city)
    {
        final List<Club> clubs = clubService.findByCity(city);

        return clubs != null
                ? new ResponseEntity<>(clubs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "Модификация данных клуба.",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @PutMapping(value = "/user/clubs")
    public ResponseEntity<?> update(
            @ApiParam(value = "Id клуба, данные которого нужно изменить.", required = true)
            @RequestParam(value = "clubId") UUID oldClubId,
            @ApiParam(value = "Модель для изменения данных клуба.", required = true)
            @RequestBody Club newClub) {
        final boolean updated = clubService.update(oldClubId, newClub);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation(
            value = "Изменение активности клуба(типо удаления/восстановления).",
            notes = "Доступ имеют только судьи(users) и админы(admins).")
    @DeleteMapping(value = "/user/clubs")
    public ResponseEntity<?> delete(
            @ApiParam(value = "Id клуба, который удаляем/восстанавливаем.", required = true)
            @RequestParam(value = "clubId") UUID clubId) {
        final boolean deleted = clubService.delete(clubId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
