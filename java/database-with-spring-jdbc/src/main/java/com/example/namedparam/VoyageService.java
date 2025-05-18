package com.example.namedparam;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VoyageService {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper<Voyage> rowMapper = (rs, rowNum) -> new Voyage(
            rs.getInt("id"),
            rs.getString("destination"),
            rs.getInt("duree_jour")
            );

    public void create(Voyage voyage) {
        String sql = "INSERT INTO voyage (id, destination, duree_jour) VALUES (:id,:destination,:dureeJour)";
        SqlParameterSource namedParamters = new BeanPropertySqlParameterSource(voyage);
        namedParameterJdbcTemplate.update(sql, namedParamters);
    }

    public List<Voyage> findAll() {
        String sql = "SELECT * FROM voyage";
        return namedParameterJdbcTemplate.query(sql, rowMapper);
    }

}
