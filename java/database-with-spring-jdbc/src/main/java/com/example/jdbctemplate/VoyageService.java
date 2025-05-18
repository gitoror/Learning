package com.example.jdbctemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VoyageService {
    private final JdbcTemplate jdbcTemplate;

    RowMapper<Voyage> rowMapper = (rs, rowNum) -> new Voyage(
            rs.getInt("id"),
            rs.getString("destination"),
            rs.getInt("duree_jour")
            );

    public void create(Voyage voyage) {
        jdbcTemplate.update("INSERT INTO voyage(id, destination, duree_jour) VALUES (?,?,?)", voyage.id(), voyage.destination(), voyage.dureeJour());
    }

    public List<Voyage> findAll() {
        return jdbcTemplate.query("SELECT * FROM voyage;", rowMapper);
    }


//    public void createVoyages(List<Voyage> voyages) {
//        jdbcTemplate.batchUpdate("INSERT INTO voyage(id, destination, duree_jour) VALUES (?,?,?)", voyages);
//    }

}
