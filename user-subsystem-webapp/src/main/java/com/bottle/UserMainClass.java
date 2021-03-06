package com.bottle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.bottle")
@EntityScan("com.bottle")
@ComponentScan("com.bottle")
public class UserMainClass {
    public static void main(String[] args) {
        SpringApplication.run(UserMainClass.class, args);
    }
}
