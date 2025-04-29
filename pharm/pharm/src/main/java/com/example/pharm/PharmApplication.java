package com.example.pharm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.pharm.repository")
public class PharmApplication {
	public static void main(String[] args) {
		SpringApplication.run(PharmApplication.class, args);
	}
}