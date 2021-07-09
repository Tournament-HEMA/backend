package com.example.backend.repository.mapper;

import com.example.backend.model.Round;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class RoundMapper implements RowMapper<Round> {
    @Override
    public Round mapRow(ResultSet rs, int rowNum) throws SQLException{
        Round round = new Round();
        round.setRoundId(UUID.fromString(rs.getString("round_id")));
        round.setNominationId(UUID.fromString(rs.getString("nomination_id")));
        round.setNumber(rs.getInt("number"));
        round.setName(rs.getString("name"));

        return round;
    }
}
