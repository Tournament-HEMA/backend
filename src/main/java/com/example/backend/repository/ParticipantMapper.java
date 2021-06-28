package com.example.backend.repository;

import com.example.backend.model.Participant;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class ParticipantMapper implements RowMapper<Participant> {
    @Override
    public Participant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Participant participant = new Participant();
        participant.setId(UUID.fromString(rs.getString("id")));
        participant.setFirstName(rs.getString("firstName"));
        participant.setLastName(rs.getString("lastName"));
        return participant;
    }
}
