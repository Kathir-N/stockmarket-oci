package com.stockmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class StockmarketOciApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockmarketOciApplication.class, args);
	}

}
