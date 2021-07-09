package com.example.backend.repository.mapper;

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
        participant.setHemaratingsId(UUID.fromString(rs.getString("hemaratings_id")));
        participant.setNumber(rs.getInt("number"));
        participant.setFirstName(rs.getString("firstname"));
        participant.setLastName(rs.getString("lastname"));
        participant.setPatronymic(rs.getString("patronymic"));
        String clubId = rs.getString("club_id");
        if(clubId != null) {
            participant.setClubId(UUID.fromString(clubId));
        } else {
            participant.setClubId(null);
        }

        return participant;
    }
}
