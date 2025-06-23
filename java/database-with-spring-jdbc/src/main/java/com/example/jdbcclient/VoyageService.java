package com.example.jdbcclient;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VoyageService {
    private final JdbcClient jdbcClient;

    public List<Voyage> findAll() {
        String sql = "SELECT * FROM voyage";
        return jdbcClient.sql(sql).query(Voyage.class).list();
    }

    public Optional<Voyage> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM voyage WHERE id=?")
                .param(id)
                .query(Voyage.class)
                .optional();
    }

    public void create(Voyage voyage) {
        int updated = jdbcClient.sql("INSERT INTO voyage (id, destination, duree_jour) VALUES (:id,:destination,:dureeJour)")
                .paramSource(voyage)
                .update();
        Assert.state(updated == 1, "Failed to create " + voyage);
    }

    public void update(Voyage voyage) {
        var updated = jdbcClient.sql("UPDATE voyage SET destination = :destination, duree_jour = :dureeJour WHERE id = :id")
                .paramSource(voyage)
                .update();
        Assert.state(updated == 1, "Failed to update " + voyage);
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM voyage WHERE id = ?")
                .param(id)
                .update();
        Assert.state(updated == 1, "Failed to delete " + id);
    }

}
