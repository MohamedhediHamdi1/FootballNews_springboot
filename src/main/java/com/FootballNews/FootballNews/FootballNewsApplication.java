package com.FootballNews.FootballNews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FootballNewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballNewsApplication.class, args);
	}

}
