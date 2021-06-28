package com.example.backend.repository;

import com.example.backend.model.Participant;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Repository
public class ParticipantRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private ParticipantMapper participantMapper;

    private static ObjectIdGenerators.UUIDGenerator uuidGenerator = new ObjectIdGenerators.UUIDGenerator();

    public List<Participant> readAll()
    {
        /* ковычки в конце запроса не нужны
        * пиши команды либо большими либо маленькими буквами. Select .. From выглядит странно */
        String sql = "Select * From participant.participants;";
        return template.query(sql, participantMapper);

    }

    /* Убери ковычки, засоряет код */
    public void create(Participant participant)
    {
        String sql = "Insert Into participant.participants (\"id\", \"firstName\", \"lastName\") Values (:id, :firstName, :lastName)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", participant.getId());
        params.put("firstName", participant.getFirstName());
        params.put("lastName", participant.getLastName());
        template.update(sql, params);
    }

    /* Очень странный метод
    1. Если у тебя есть сущьность oldParticipant и newParticipant, значит у тебя есть id старого фихтуна
    2. Ни кто тебе не мешает строить параметризированный запрос через конкантинацию: sql += "firstname = :firstname" и тому подобное
    3. Много продублированного кода
    * */
    public boolean update(Participant oldParticipant, Participant newParticipant) {
        String sql = "";
        Map<String, Object> params = new HashMap<>();
        if (newParticipant.getFirstName() != null) {
            if (newParticipant.getLastName() != null) {
                sql = "UPDATE participant.participants SET \"firstName\" = ':newFirstName', \"lastName\" = ':newLastName' WHERE \"firstName\" = ':oldFirstName' AND \"lastName\" = ':oldLastName'";
                params.put("newFirstName", newParticipant.getFirstName());
                params.put("newLastName", newParticipant.getLastName());
                params.put("oldFirstName", oldParticipant.getFirstName());
                params.put("oldLastName", oldParticipant.getFirstName());
            } else{
                sql = "UPDATE participant.participants SET \"firstName\" = ':newFirstName' WHERE \"firstName\" = ':oldFirstName' AND \"lastName\" = ':oldLastName'";
                params.put("newFirstName", newParticipant.getFirstName());
                params.put("oldFirstName", oldParticipant.getFirstName());
                params.put("oldLastName", oldParticipant.getFirstName());
            }
        } else {
            if (newParticipant.getLastName() != null) {
                sql = "UPDATE participant.participants SET \"lastName\" = ':newLastName' WHERE \"firstName\" = ':oldFirstName' AND \"lastName\" = ':oldLastName'";
                params.put("newLastName", newParticipant.getLastName());
                params.put("oldFirstName", oldParticipant.getFirstName());
                params.put("oldLastName", oldParticipant.getFirstName());
            }
        }
        // Некрасивый код
        if(!sql.isEmpty()) {
            template.update(sql, params);
            return true;
        } else { return false;}
    }
}
