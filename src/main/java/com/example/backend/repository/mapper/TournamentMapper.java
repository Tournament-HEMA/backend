package com.example.backend.repository.mapper;

import com.example.backend.model.Tournament;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class TournamentMapper implements RowMapper<Tournament> {
    public Tournament mapRow(ResultSet rs, int rowNum) throws SQLException{
        Tournament tournament = new Tournament();
        tournament.setTournamentId(UUID.fromString((rs.getString("tournament_id"))));
        tournament.setName(rs.getString("name"));
        tournament.setBeginning(rs.getDate("beginning"));
        tournament.setEnding(rs.getDate("ending"));

        return tournament;
    }
}
