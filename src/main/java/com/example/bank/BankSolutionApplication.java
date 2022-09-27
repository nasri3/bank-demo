package com.example.bank;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.bank.model")
@EnableJpaRepositories({"com.example.bank.dao"})
public class BankSolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSolutionApplication.class, args);
	}
}
