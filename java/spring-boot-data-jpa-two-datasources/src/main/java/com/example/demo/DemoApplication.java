package com.example.demo;

import com.example.demo.mysql.MysqlClientsRepository;
import com.example.demo.pgsql.PgsqlClientsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		MysqlClientsRepository mysqlClientsRepository = context.getBean(MysqlClientsRepository.class);
		PgsqlClientsRepository pgsqlClientsRepository = context.getBean(PgsqlClientsRepository.class);
		System.out.println(mysqlClientsRepository.findAll());
		System.out.println(pgsqlClientsRepository.findAll());
	}

}
