package com.example.movieRankedProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@PropertySource("classpath:messages.properties")
public class MovieRankedProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieRankedProjectApplication.class, args);
	}

}
