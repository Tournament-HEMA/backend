package com.example.backend.repository.mapper;

import com.example.backend.model.Club;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class ClubMapper implements RowMapper<Club> {
    @Override
    public Club mapRow(ResultSet rs, int rowNum) throws SQLException {
        Club club = new Club();
        club.setClubId(UUID.fromString(rs.getString("club_id")));
        club.setClubname(rs.getString("clubname"));
        club.setCity(rs.getString("city"));
        club.setActive(rs.getBoolean("active"));

        return club;
    }
}
