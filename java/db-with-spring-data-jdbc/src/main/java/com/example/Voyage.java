package com.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
public class Voyage {
    @Id
    private Integer id;
    private String destination;
    private Integer dureeJour;
    private LocalDateTime createdAt;


    public Voyage(String destination, Integer dureeJour) {
        this.destination = destination;
        this.dureeJour = dureeJour;
        this.createdAt = LocalDateTime.now();
    }
}
