package com.example.exchangenotifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExchangeNotifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeNotifierApplication.class, args);
	}
}
