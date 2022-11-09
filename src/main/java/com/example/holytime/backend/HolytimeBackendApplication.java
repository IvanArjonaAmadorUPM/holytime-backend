package com.example.holytime.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HolytimeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolytimeBackendApplication.class, args);
		System.out.println("El servidor está esperando solicitudes");
	}

}
