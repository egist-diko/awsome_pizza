package com.awsome_pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.awsome_pizza.model")
public class AwsomePizzaApplication {
	public static void main(String[] args) {
		SpringApplication.run(AwsomePizzaApplication.class, args);
	}
}

