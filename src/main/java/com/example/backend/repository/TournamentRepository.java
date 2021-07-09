package com.example.backend.repository;

import com.example.backend.model.Tournament;
import com.example.backend.repository.mapper.TournamentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TournamentRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private TournamentMapper tournamentMapper;

    public List<Tournament> findAll()
    {
        String sql = "SELECT * FROM tournament.tournaments ORDER BY name";
        return template.query(sql, tournamentMapper);
    }

    public Tournament search(UUID tournamentId)
    {
        String sql = "SELECT * FROM tournament.tournaments WHERE tournament_id = :tournamentId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("tournamentId", tournamentId);
        return template.queryForObject(sql, params, tournamentMapper);
    }

    public Tournament search(String name)
    {
        String sql = "SELECT * FROM tournament.tournaments WHERE name = :name";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", name);
        return template.queryForObject(sql, params, tournamentMapper);
    }

    public boolean create(Tournament tournament) {
        String sql = "INSERT INTO tournament.tournaments (tournament_id, name, beginning, ending) VALUES (:tournamentId, :name, :beginning, :ending)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("tournamentId", UUID.randomUUID())
                .addValue("name", tournament.getName())
                .addValue("beginning", tournament.getBeginning())
                .addValue("ending", tournament.getEnding());
        try {
            template.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean check(UUID tournamentId) {
        return search(tournamentId) == null;
    }

    public boolean update(UUID tournamentId, Tournament newTournament) {
        if (check(tournamentId)) {
            return false;
        }
        String sql = "UPDATE tournament.tournaments SET name = :newName, beginning = :newBeginning, ending = :newEnding WHERE tournament_id = :tournamentId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("newName", newTournament.getName())
                .addValue("newBeginning", newTournament.getBeginning())
                .addValue("newEnding", newTournament.getEnding())
                .addValue("tournamentId", tournamentId);
        try {
            template.update(sql, params);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public boolean delete(UUID tournamentId) {
        if (check(tournamentId)) {
            return false;
        }
        String sql = "DELETE FROM tournament.tournaments WHERE tournament_id = :tournamentId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("tournamentId", tournamentId);
        try {
            template.update(sql, params);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
