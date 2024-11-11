package com.piyush004.SportsApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SportsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsApiApplication.class, args);
	}

}
