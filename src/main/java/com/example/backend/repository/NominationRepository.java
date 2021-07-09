package com.example.backend.repository;

import com.example.backend.model.Nomination;
import com.example.backend.repository.mapper.NominationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class NominationRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private NominationMapper nominationMapper;

    public List<Nomination> findAll()
    {
        String sql = "SELECT * FROM nomination.nominations ORDER BY name";
        return template.query(sql, nominationMapper);
    }

    public Nomination search(UUID nominationId)
    {
        String sql = "SELECT * FROM nomination.nominations WHERE nomination_id = :nominationId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nominationId", nominationId);
        return template.queryForObject(sql, params, nominationMapper);
    }

    public List<Nomination> searchByTournament(UUID tournamentId)
    {
        String sql = "SELECT * FROM nomination.nominations WHERE tournament_id = :tournamentId ORDER BY name";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("tournamentId", tournamentId);
        return template.query(sql, params, nominationMapper);
    }

    public List<Nomination> search(String name)
    {
        String sql = "SELECT * FROM nomination.nominations WHERE name = :name";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", name);
        return template.query(sql, params, nominationMapper);
    }

    public List<Nomination> searchByCategory(String category)
    {
        String sql = "SELECT * FROM nomination.nominations WHERE category = :category ORDER BY name";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("category", category);
        return template.query(sql, params, nominationMapper);
    }

    public List<Nomination> search(UUID tournamentId, String category)
    {
        String sql = "SELECT * FROM nomination.nominations WHERE tournament_id = :tournamentId AND category = :category ORDER BY name";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("tournamentId", tournamentId)
                .addValue("category", category);
        return template.query(sql, params, nominationMapper);
    }

    public boolean create(Nomination nomination) {
        String sql = "INSERT INTO nomination.nominations (nomination_id, tournament_id, category, name) VALUES (:nominationId, :tournamentId, :category, :name)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nominationId", UUID.randomUUID())
                .addValue("tournamentId", nomination.getTournamentId())
                .addValue("category", nomination.getCategory())
                .addValue("name", nomination.getName());
        try {
            template.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean check(UUID nominationId) {
        return search(nominationId) == null;
    }

    public boolean update(UUID nominationId, Nomination newNomination) {
        if (check(nominationId)) {
            return false;
        }
        String sql = "UPDATE nomination.nominations SET tournament_id = :newTournamentId, category = :newCategory, name = :newName WHERE nomination_id = :nominationId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("newTournamentId", newNomination.getTournamentId())
                .addValue("newCategory", newNomination.getCategory())
                .addValue("newName", newNomination.getName())
                .addValue("nominationId", nominationId);
        try {
            template.update(sql, params);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public boolean delete(UUID nominationId) {
        if (check(nominationId)) {
            return false;
        }
        String sql = "DELETE FROM nomination.nominations WHERE nomination_id = :nominationId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nominationId", nominationId);
        try {
            template.update(sql, params);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
