package com.example.backend.repository;

import com.example.backend.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
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
        return template.query(sql, participantMapper);
    }

    public List<Participant> search(String firstname, String lastname)
    {
        String sql = "SELECT * FROM participant.participants WHERE firstname = :firstname AND lastname = :lastname ORDER BY number";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstname", firstname)
                .addValue("lastname", lastname);
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

    public boolean create(Participant participant) {
        String sql = "INSERT INTO participant.participants (id, firstname, lastname) VALUES (:id, :firstname, :lastname)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", UUID.randomUUID())
                .addValue("firstname", participant.getFirstName())
                .addValue("lastname", participant.getLastName());
        try {
            template.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean check(int number) {
        if (search(number) != null) return true;
        else return false;
    }

    public boolean update(int oldParticipantNumber, Participant newParticipant) {
        if (!check(oldParticipantNumber)) return false;
        String sql = "UPDATE participant.participants SET";

        if (newParticipant.getFirstName() != null) {
            if (newParticipant.getLastName() != null) {
                sql += " firstname = :newFirstName, lastname = :newLastName";
            } else {
                sql += " firstname = :newFirstName";
            }
        } else {
            if (newParticipant.getLastName() != null) {
                sql += " lastname = :newLastName";
            }
        }

        sql += " WHERE number = :number";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("newFirstName", newParticipant.getFirstName())
                .addValue("newLastName", newParticipant.getLastName())
                .addValue("number", oldParticipantNumber);
        template.update(sql, params);
        return true;
    }

    public boolean delete(int number) {
        if (!check(number)) return false;
        String sql = "DELETE FROM participant.participants WHERE number = :number";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("number", number);
        template.update(sql, params);
        return true;
    }
}
