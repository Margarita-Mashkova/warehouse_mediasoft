package com.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * The entry point for launching a Spring Boot-based application
 * @author Margarita Mashkova
 * */
@SpringBootApplication
public class WarehouseApplication {
	/**
	 * The main function that calls the run method of the Spring Application class, which starts the Spring Boot application.
	 * This allows to initialize and configure all components of the application.
	 * */
	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

}
