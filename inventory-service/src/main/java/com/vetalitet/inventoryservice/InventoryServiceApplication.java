package com.vetalitet.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// add package implicitly to make GlobalExceptionHandler work
@SpringBootApplication(
		scanBasePackages = {
				"com.vetalitet"
		}
)
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
