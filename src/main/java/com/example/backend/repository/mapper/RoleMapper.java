package com.example.backend.repository.mapper;

import com.example.backend.model.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class RoleMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setId(UUID.fromString(rs.getString("id")));
        role.setName(rs.getString("name"));

        return role;
    }
}
