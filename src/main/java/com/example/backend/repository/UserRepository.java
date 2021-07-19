package com.example.backend.repository;

import com.example.backend.model.User;
import com.example.backend.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        String sql = "SELECT * FROM auth.users";

        return template.query(sql, userMapper);
    }

    public User findByUsername(String userName) {
        String sql = "SELECT * FROM auth.users WHERE username = :userName";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("userName", userName);

        return template.queryForObject(sql, params, userMapper);
    }

    public List<User> findByFirstName(String firstName){
        String sql = "SELECT * FROM auth.users WHERE firstname = :firstName";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstName", firstName);

        return template.query(sql, params, userMapper);
    }

    public List<User> findByFirstNameAndLastName(String firstName, String lastName){
        String sql = "SELECT * FROM auth.users WHERE firstname = :firstName AND lastname = :lastName";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstName", firstName)
                .addValue("lastName", lastName);

        return template.query(sql, params, userMapper);
    }

    public List<User> findByFirstNameLastNameAndPatronymic(String firstName, String lastName, String patronymic){
        String sql = "SELECT * FROM auth.users WHERE firstname = :firstName AND lastname = :lastName AND patronymic = :patronymic";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstName", firstName)
                .addValue("lastName", lastName)
                .addValue("patronymic", patronymic);

        return template.query(sql, params, userMapper);
    }

    public User findById(UUID id){
        String sql = "SELECT * FROM auth.users WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        return template.queryForObject(sql, params, userMapper);
    }

    public boolean saveUser(User user){
        String sql = "INSERT INTO auth.users (id, username, password, firstname, lastname, patronymic) VALUES (:id, :username, :password, :firstname, :lastname, :patronymic)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("username", user.getUsername())
                .addValue("password", user.getPassword())
                .addValue("firstname", user.getFirstName())
                .addValue("lastname", user.getLastName())
                .addValue("patronymic", user.getPatronymic());
        try {
            template.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(UUID oldId, User user) {
        if (findById(oldId) == null) {
            return false;
        }
        String sql = "UPDATE auth.users SET firstname = :firstname, lastname = :lastname, patronymic = :patronymic WHERE id = :oldId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstname", user.getFirstName())
                .addValue("lastname", user.getLastName())
                .addValue("patronymic", user.getPatronymic())
                .addValue("oldId", oldId);
        try {
            template.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(UUID id){
        String sql = "DELETE FROM auth.users WHERE id = :id";
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
