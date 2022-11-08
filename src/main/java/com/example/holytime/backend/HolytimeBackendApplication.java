package com.example.holytime.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HolytimeBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(HolytimeBackendApplication.class, args);
	}
	@GetMapping
	public String hello() {
		return "Hello World!";
	}
}
