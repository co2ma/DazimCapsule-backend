package com.co2ma.dazimcapsule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DazimcapsuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DazimcapsuleApplication.class, args);
	}

}
