package com.bottle.userWall.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.bottle.userWall.home.repository")
@EntityScan("com.bottle.userWall.home.entity")
@ComponentScan("com.bottle.userWall.home")
public class HomeApplication {

	public static void main(String[] args) {SpringApplication.run(HomeApplication.class, args);
	}
}
