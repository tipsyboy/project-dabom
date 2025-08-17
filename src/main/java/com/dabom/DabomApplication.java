package com.dabom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DabomApplication {

	public static void main(String[] args) {
		SpringApplication.run(DabomApplication.class, args);
	}

}
