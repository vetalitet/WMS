package com.vetalitet.facadeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"com.vetalitet"
		}
)
public class ProductInventoryFacadeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductInventoryFacadeServiceApplication.class, args);
	}

}
