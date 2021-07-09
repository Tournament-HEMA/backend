package com.example.backend.repository;

import com.example.backend.model.Club;
import com.example.backend.repository.mapper.ClubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ClubRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private ClubMapper clubMapper;

    public List<Club> findAll()
    {
        String sql = "SELECT * FROM club.clubs ORDER BY clubname";
        return template.query(sql, clubMapper);
    }

    public Club search(UUID clubId)
    {
        String sql = "SELECT * FROM club.clubs WHERE club_id = :clubId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("clubId", clubId);
        return template.queryForObject(sql, params, clubMapper);
    }

    public Club search(String clubname)
    {
        String sql = "SELECT * FROM club.clubs WHERE clubname = :clubname ORDER BY clubname";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("clubname", clubname);
        return template.queryForObject(sql, params, clubMapper);
    }

    public List<Club> search(boolean active)
    {
        String sql = "SELECT * FROM club.clubs WHERE active = :active ORDER BY clubname";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("active", active);
        return template.query(sql, params, clubMapper);
    }

    public List<Club> searchByCity(String city)
    {
        String sql = "SELECT * FROM club.clubs WHERE city = :city ORDER BY clubname";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("city", city);
        return template.query(sql, params, clubMapper);
    }

    public boolean create(Club club) { //todo протестить, вероятно params имеет лишнее значение
        String sql;
        if(club.getCity()!=null) {
            sql = "INSERT INTO club.clubs (club_id, clubname, city) VALUES (:clubId, :clubname, :city)";
        } else {
            sql = "INSERT INTO club.clubs (club_id, clubname) VALUES (:clubId, :clubname)";
        }
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("clubId", UUID.randomUUID())
                .addValue("clubname", club.getClubname())
                .addValue("city", club.getCity());
        try {
            template.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean check(UUID clubId) {
        return search(clubId) == null;
    }

    public boolean update(UUID oldClubId, Club newClub) {
        if (check(oldClubId)) {
            return false;
        }
        String sql = "UPDATE club.clubs SET clubname = :newClubname, city = :newCity, active = :newActive WHERE club_id = :oldClubId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("newClubname", newClub.getClubname())
                .addValue("newCity", newClub.getCity())
                .addValue("newActive", newClub.isActive())
                .addValue("oldClubId", oldClubId);
        template.update(sql, params);
        return true;
    }

    public boolean delete(UUID clubId) { //todo может этот метод назвать setActive/changeActive? ставит противоположное значение
        if (check(clubId)) {
            return false;
        }
        String sql = "UPDATE club.clubs SET active = :newActive WHERE club_id = :clubId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("newActive", !search(clubId).isActive())
                .addValue("clubId", clubId);
        template.update(sql, params);
        return true;
    }
}
