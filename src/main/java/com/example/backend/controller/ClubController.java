package com.example.backend.controller;

import com.example.backend.model.Club;
import com.example.backend.service.ClubService;
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

    @PostMapping(value = "/clubs")
    public ResponseEntity<?> create(@RequestBody Club club) {
        final boolean created = clubService.create(club);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/clubs")
    public ResponseEntity<List<Club>> findAll() {
        final List<Club> clubs = clubService.findAll();

        return clubs != null &&  !clubs.isEmpty()
                ? new ResponseEntity<>(clubs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/clubs/findById")
    public ResponseEntity<Club> findByClubId(@RequestParam(value = "clubId") UUID clubId)
    {
        final Club club = clubService.findByClubId(clubId);

        return (club != null)
                ? new ResponseEntity<>(club, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/clubs/findByClubname")
    public ResponseEntity<Club> findByClubname(@RequestParam(value = "clubname") String clubname)
    {
        final Club club = clubService.findByClubname(clubname);

        return club != null
                ? new ResponseEntity<>(club, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/clubs/findByActive")
    public ResponseEntity<List<Club>> findByActive(@RequestParam(value = "active") boolean active)
    {
        final List<Club> clubs = clubService.findByActive(active);

        return clubs != null
                ? new ResponseEntity<>(clubs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/clubs/findByCity")
    public ResponseEntity<List<Club>> findByCity(@RequestParam(value = "city") String city)
    {
        final List<Club> clubs = clubService.findByCity(city);

        return clubs != null
                ? new ResponseEntity<>(clubs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/clubs")
    public ResponseEntity<?> update(@RequestParam(value = "clubId") UUID oldClubId,
                                    @RequestBody Club newClub) {
        final boolean updated = clubService.update(oldClubId, newClub);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/clubs")
    public ResponseEntity<?> delete(@RequestParam(value = "clubId") UUID clubId) {
        final boolean deleted = clubService.delete(clubId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
