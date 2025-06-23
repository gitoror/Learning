package com.example.jdbcclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    VoyageService voyageService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        voyageService.create(new Voyage(1,"Paris", 2));
        List<Voyage> voyages = voyageService.findAll();
        for (Voyage voyage : voyages) {
            System.out.println(voyage);
        }
        voyageService.update(new Voyage(1, "London", 3));
        Optional<Voyage> voyage = voyageService.findById(1);
        if (voyage.isPresent()) {
            System.out.println(voyage);
        } else {
            System.out.println("Didnt find voyage");
        }
        voyageService.delete(1);
        voyages = voyageService.findAll();
        System.out.println("Voyages "+voyages);
    }
}
