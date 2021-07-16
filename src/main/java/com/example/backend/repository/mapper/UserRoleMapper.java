package com.example.backend.repository.mapper;

import com.example.backend.model.UserRole;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class UserRoleMapper implements RowMapper<UserRole> {
    @Override
    public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRole userRole = new UserRole();
        userRole.setId(UUID.fromString(rs.getString("id")));
        userRole.setUserId(UUID.fromString(rs.getString("user_id")));
        userRole.setRoleId(UUID.fromString(rs.getString("role_id")));

        return userRole;
    }
}
