package com.example.backend.repository;

import com.example.backend.model.Participant;
import com.example.backend.repository.mapper.ParticipantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ParticipantRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private ParticipantMapper participantMapper;

    public List<Participant> findAll()
    {
        String sql = "SELECT * FROM participant.participants ORDER BY number";
        return template.query(sql, participantMapper);
    }

    public List<Participant> search(String firstname)
    {
        String sql = "SELECT * FROM participant.participants WHERE firstname = :firstname ORDER BY number";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstname", firstname);
        return template.query(sql, params, participantMapper);
    }

    public List<Participant> search(String firstname, String lastname)
    {
        String sql = "SELECT * FROM participant.participants WHERE firstname = :firstname AND lastname = :lastname ORDER BY number";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstname", firstname)
                .addValue("lastname", lastname);
        return template.query(sql, params, participantMapper);
    }

    public List<Participant> search(String firstname, String lastname, String patronymic)
    {
        String sql = "SELECT * FROM participant.participants WHERE firstname = :firstname AND lastname = :lastname AND patronymic = :patronymic ORDER BY number";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstname", firstname)
                .addValue("lastname", lastname)
                .addValue("patronymic", patronymic);
        return template.query(sql, params, participantMapper);
    }

    public Participant search(String firstname, String lastname, int number)
    {
        String sql = "SELECT * FROM participant.participants WHERE firstname = :firstname AND lastname = :lastname AND number = :number";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstname", firstname)
                .addValue("lastname", lastname)
                .addValue("number", number);
        return template.queryForObject(sql, params, participantMapper);
    }

    public Participant search(int number)
    {
        String sql = "SELECT * FROM participant.participants WHERE number = :number";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("number", number);
        return template.queryForObject(sql, params, participantMapper);
    }

    public List<Participant> search(UUID clubId)
    {
        String sql = "SELECT * FROM participant.participants WHERE club_id = :clubId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("clubId", clubId);
        return template.query(sql, params, participantMapper);
    }

    public boolean create(Participant participant) {
        String sql = "INSERT INTO participant.participants (hemaratings_id, firstname, lastname, patronymic, club_id) VALUES (:hemaratingsId, :firstname, :lastname, :patronymic, :clubId)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("hemaratingsId", UUID.randomUUID())
                .addValue("firstname", participant.getFirstName())
                .addValue("lastname", participant.getLastName())
                .addValue("patronymic", participant.getPatronymic())
                .addValue("clubId", participant.getClubId());
        try {
            template.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean check(int number) {
        return search(number) == null;
    }

    public boolean update(int oldParticipantNumber, Participant newParticipant) {
        if (check(oldParticipantNumber)) {
            return false;
        }
        String sql = "UPDATE participant.participants SET firstname = :newFirstName, lastname = :newLastName, patronymic = :newPatronymic, club_id = :newClubId WHERE number = :number";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("newFirstName", newParticipant.getFirstName())
                .addValue("newLastName", newParticipant.getLastName())
                .addValue("newPatronymic", newParticipant.getPatronymic())
                .addValue("newClubId", newParticipant.getClubId())
                .addValue("number", oldParticipantNumber);
        try {
            template.update(sql, params);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public boolean delete(int number) {
        if (check(number)) {
            return false;
        }
        String sql = "DELETE FROM participant.participants WHERE number = :number";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("number", number);
        try {
            template.update(sql, params);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
