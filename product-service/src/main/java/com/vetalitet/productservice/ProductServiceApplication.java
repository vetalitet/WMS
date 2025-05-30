package com.vetalitet.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// add package implicitly to make GlobalExceptionHandler work
@SpringBootApplication(
		scanBasePackages = {
				"com.vetalitet"
		}
)
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
