package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@SpringBootApplication
public class Application {

//    @Autowired VoyageRepository voyageRepository;

    public static void main(String... args) {
        SpringApplication.run(Application.class);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        voyageRepository.save(new Voyage("Paris", 2));
//    }
    @Bean
    CommandLineRunner run(VoyageRepository voyageRepository) {
        return args -> {
            voyageRepository.save(new Voyage("Paris", 2));
        };
    }
}
