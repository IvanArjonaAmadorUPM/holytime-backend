package com.example.holytime.backend;

import com.example.holytime.backend.firebase.FirebaseInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HolytimeBackendApplication {

	@Autowired
	FirebaseInitializer firebaseInitializer;
	public static void main(String[] args) {
		SpringApplication.run(HolytimeBackendApplication.class, args);
		System.out.println("El servidor está esperando solicitudes");
	}
	@Bean
	public CommandLineRunner demoData(){
		System.out.println("Inicializando Firebase");
		return args -> {
			firebaseInitializer.init();
		};

	}
}
