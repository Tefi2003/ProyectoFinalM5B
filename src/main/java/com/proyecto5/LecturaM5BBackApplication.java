package com.proyecto5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LecturaM5BBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LecturaM5BBackApplication.class, args);
	}

}
