package com.example.productManagement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProductManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementApplication.class, args);
	}






}
