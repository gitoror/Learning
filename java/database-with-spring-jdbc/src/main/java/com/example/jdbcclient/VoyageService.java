package com.example.jdbcclient;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VoyageService {
    private final JdbcClient jdbcClient;

    public void create(Voyage voyage) {
        String sql = "INSERT INTO voyage (id, destination, duree_jour) VALUES (:id,:destination,:dureeJour)";
        SqlParameterSource namedParamters = new BeanPropertySqlParameterSource(voyage);
        jdbcClient.sql(sql)
                .paramSource(voyage)
                .update();
    }

    public List<Voyage> findAll() {
        String sql = "SELECT * FROM voyage";
        return jdbcClient.sql(sql).query(Voyage.class).list();
    }

}
