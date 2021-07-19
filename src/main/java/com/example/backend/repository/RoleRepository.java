package com.example.backend.repository;

import com.example.backend.model.Role;
import com.example.backend.model.UserRole;
import com.example.backend.repository.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RoleRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> findAll(){
        String sql = "SELECT * FROM auth.roles";
        return template.query(sql, roleMapper);
    }

    public Role search(UUID id){
        String sql = "SELECT * FROM auth.roles WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return template.queryForObject(sql, params, roleMapper);
    }

    public Set<Role> search(List<UserRole> userRoles) { //todo высокая вероятность испачкаться грязью..
        if (userRoles == null) {
            return Collections.EMPTY_SET;
        }

        List<UUID> roleId = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            roleId.add(userRole.getRoleId());
        }
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", roleId);

        String sql = "SELECT * FROM auth.roles WHERE id IN :id";
        List<Role> roles = template.query(sql, params, roleMapper);

        return new HashSet<>(roles);
    }

    public Role search(String name){
        String sql = "SELECT * FROM auth.roles WHERE name = :name";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", name);
        return template.queryForObject(sql, params, roleMapper);
    }

    public boolean create(Role role) {
        String sql = "INSERT INTO auth.roles (id, name) VALUES (:id, :name)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", UUID.randomUUID())
                .addValue("name", role.getName());
        try {
            template.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(UUID oldId, Role role) {
        if (search(oldId) == null) {
            return false;
        }
        String sql = "UPDATE auth.roles SET name = :name WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("oldId", oldId)
                .addValue("name", role.getName());
        try {
            template.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(UUID id){
        if (search(id) == null) {
            return false;
        }
        String sql = "DELETE FROM auth.roles WHERE id = :id";
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
