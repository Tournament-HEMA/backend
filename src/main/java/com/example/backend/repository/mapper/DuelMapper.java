package com.example.backend.repository.mapper;

import com.example.backend.model.Duel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class DuelMapper implements RowMapper<Duel> {
    @Override
    public Duel mapRow(ResultSet rs, int rowNum) throws SQLException{
        Duel duel = new Duel();
        duel.setDuelId(UUID.fromString(rs.getString("duel_id")));
        duel.setRoundId(UUID.fromString(rs.getString("round_id")));
        duel.setNumber(rs.getInt("number"));
        duel.setFirstOpponent(UUID.fromString(rs.getString("first_opponent")));
        duel.setSecondOpponent(UUID.fromString(rs.getString("second_opponent")));
        duel.setFirstOpponentPoints(rs.getInt("first_opponent_points"));
        duel.setSecondOpponentPoints(rs.getInt("second_opponent_points"));

        return duel;
    }
}
