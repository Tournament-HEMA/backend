package com.example.backend.repository;

import com.example.backend.model.Duel;
import com.example.backend.repository.mapper.DuelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DuelRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private DuelMapper duelMapper;

    public List<Duel> findAll()
    {
        String sql = "SELECT * FROM duel.duels";
        return template.query(sql, duelMapper);
    }

    public Duel search(UUID duelId)
    {
        String sql = "SELECT * FROM duel.duels WHERE duel_id = :duelId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("duelId", duelId);
        return template.queryForObject(sql, params, duelMapper);
    }

    public List<Duel> searchByRoundId(UUID roundId)
    {
        String sql = "SELECT * FROM duel.duels WHERE round_id = :roundId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("roundId", roundId);
        return template.query(sql, params, duelMapper);
    }

    public Duel search(UUID roundId, int number)
    {
        String sql = "SELECT * FROM duel.duels WHERE round_id = :roundId AND number = :number";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("roundId", roundId)
                .addValue("number", number);
        return template.queryForObject(sql, params, duelMapper);
    }

    public List<Duel> searchDuelsOfParticipant(UUID participantId)
    {
        String sql = "SELECT * FROM duel.duels WHERE first_opponent = :participantId OR second_opponent = :participantId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("participantId", participantId);
        return template.query(sql, params, duelMapper);
    }

    public List<Duel> searchDuelsOfParticipant(UUID participantId, UUID roundId)
    {
        String sql = "SELECT * FROM duel.duels WHERE (first_opponent = :participantId OR second_opponent = :participantId) AND round_id = :roundId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("participantId", participantId)
                .addValue("roundId", roundId);
        return template.query(sql, params, duelMapper);
    }

    public boolean create(Duel duel) {
        String sql = "INSERT INTO duel.duels (duel_id, round_id, number, first_opponent, second_opponent) VALUES (:duelId, :roundId, :number, :firstOpponent, :secondOpponent)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("duelId", UUID.randomUUID())
                .addValue("roundId", duel.getRoundId())
                .addValue("number", duel.getNumber())
                .addValue("firstOpponent", duel.getFirstOpponent())
                .addValue("secondOpponent", duel.getSecondOpponent());
        try {
            template.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean check(UUID duelId) {
        return search(duelId) == null;
    }

    public boolean update(UUID duelId, Duel newDuel) {
        if (check(duelId)) {
            return false;
        }
        String sql = "UPDATE duel.duels SET round_id = :newRoundId, number = :newNumber, first_opponent = :newFirstOpponent, second_opponent = :newSecondOpponent, first_opponent_points = :newFirstOpponentPoints, second_opponent_points = :newSecondOpponentPoints WHERE duel_id = :duelId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("newRoundId", newDuel.getRoundId())
                .addValue("newNumber", newDuel.getNumber())
                .addValue("newFirstOpponent", newDuel.getFirstOpponent())
                .addValue("newSecondOpponent", newDuel.getSecondOpponent())
                .addValue("newFirstOpponentPoints", newDuel.getFirstOpponentPoints())
                .addValue("newSecondOpponentPoints", newDuel.getSecondOpponentPoints())
                .addValue("duelId", duelId);

        template.update(sql, params);
        return true;
    }

    public boolean delete(UUID duelId) {
        if (check(duelId)) {
            return false;
        }
        String sql = "DELETE FROM duel.duels WHERE duel_id = :duelId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("duelId", duelId);
        template.update(sql, params);
        return true;
    }
}
