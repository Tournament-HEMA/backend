package com.example.backend.repository;

import com.example.backend.model.Round;
import com.example.backend.repository.mapper.RoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RoundRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private RoundMapper roundMapper;

    public List<Round> findAll()
    {
        String sql = "SELECT * FROM round.rounds";
        return template.query(sql, roundMapper);
    }

    public Round search(UUID roundId)
    {
        String sql = "SELECT * FROM round.rounds WHERE round_id = :roundId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("roundId", roundId);
        return template.queryForObject(sql, params, roundMapper);
    }

    public List<Round> searchByNomination(UUID nominationId)
    {
        String sql = "SELECT * FROM round.rounds WHERE nomination_id = :nominationId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nominationId", nominationId);
        return template.query(sql, params, roundMapper);
    }

    public Round search(UUID nominationId, int number)
    {
        String sql = "SELECT * FROM round.rounds WHERE nomination_id = :nominationId AND number = :number";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nominationId", nominationId)
                .addValue("number", number);
        return template.queryForObject(sql, params, roundMapper);
    }

    public boolean create(Round round) {
        String sql;
        if(round.getName()!=null) {
            sql = "INSERT INTO round.rounds (round_id, nomination_id, number, name) VALUES (:roundId, :nominationId, :number, :name)";
        } else {
            sql = "INSERT INTO round.rounds (round_id, nomination_id, number) VALUES (:roundId, :nominationId, :number)";
        }
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("roundId", UUID.randomUUID())
                .addValue("nominationId", round.getNominationId())
                .addValue("number", round.getNumber())
                .addValue("name", round.getName());
        try {
            template.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean check(UUID roundId) {
        return search(roundId) == null;
    }

    public boolean update(UUID roundId, Round newRound) {
        if (check(roundId)) {
            return false;
        }
        String sql = "UPDATE round.rounds SET nomination_id = :newNominationId, number = :newNumber, name = :newName WHERE round_id = :roundId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("newNominationId", newRound.getNominationId())
                .addValue("newNumber", newRound.getNumber())
                .addValue("newName", newRound.getName())
                .addValue("roundId", roundId);
        template.update(sql, params);
        return true;
    }

    public boolean delete(UUID roundId) {
        if (check(roundId)) {
            return false;
        }
        String sql = "DELETE FROM round.rounds WHERE round_id = :roundId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("roundId", roundId);
        template.update(sql, params);
        return true;
    }
}
