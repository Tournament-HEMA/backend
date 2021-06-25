package com.example.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipantRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    public void readAll()
    {

    }
}
