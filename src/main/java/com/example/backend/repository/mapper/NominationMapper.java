package com.example.backend.repository.mapper;

import com.example.backend.model.Nomination;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class NominationMapper implements RowMapper<Nomination> {
    @Override
    public Nomination mapRow(ResultSet rs, int rowNum) throws SQLException{
        Nomination nomination = new Nomination();
        nomination.setNominationId(UUID.fromString(rs.getString("nomination_id")));
        nomination.setTournamentId(UUID.fromString(rs.getString("tournament_id")));
        nomination.setCategory(rs.getString("category"));
        nomination.setName(rs.getString("name"));

        return nomination;
    }
}
