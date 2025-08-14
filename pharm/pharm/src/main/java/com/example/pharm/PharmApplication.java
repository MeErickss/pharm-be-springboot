package com.example.pharm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication(
		exclude = {
				SecurityAutoConfiguration.class
		}
)
@EntityScan(basePackages = "com.example.pharm.model")
@EnableJpaRepositories(basePackages = "com.example.pharm.repository")
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class PharmApplication {
	public static void main(String[] args) {
		SpringApplication.run(PharmApplication.class, args);
	}
}
