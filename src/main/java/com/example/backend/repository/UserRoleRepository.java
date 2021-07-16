package com.example.backend.repository;

import com.example.backend.model.UserRole;
import com.example.backend.repository.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRoleRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<UserRole> findAll(){
        String sql = "SELECT * FROM auth.users_roles";
        return template.query(sql, userRoleMapper);
    }

    public UserRole search(UUID id){
        String sql = "SELECT * FROM auth.users_roles WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return template.queryForObject(sql, params, userRoleMapper);
    }

    public List<UserRole> findByUserId(UUID userId){
        String sql = "SELECT * FROM auth.users_roles WHERE user_id = :userId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);
        return template.query(sql, params, userRoleMapper);
    }

    public List<UserRole> findByRoleId(UUID roleId){
        String sql = "SELECT * FROM auth.users_roles WHERE role_id = :roleId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("roleId", roleId);
        return template.query(sql, params, userRoleMapper);
    }

    public boolean addRole(UserRole userRole) {
        String sql = "INSERT INTO auth.users_roles (id, user_id, role_id) VALUES (:id, :userId, :roleId)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", UUID.randomUUID())
                .addValue("userId", userRole.getUserId())
                .addValue("roleId", userRole.getRoleId());
        try {
            template.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(UUID id) {
        if (search(id) == null) {
            return false;
        }
        String sql = "DELETE FROM auth.users_roles WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            template.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
