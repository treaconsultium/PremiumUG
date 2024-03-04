package com.dailycode.premiumug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PremiumUgServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PremiumUgServiceApplication.class, args);
	}

}
