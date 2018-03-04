package com.bottle.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.bottle.event.model.repository")
@EntityScan("com.bottle.event.model.entity")
@ComponentScan("com.bottle.event")
public class MainClass {
    public static void main(String[] args) {
        SpringApplication.run(MainClass.class, args);
    }
}

//TODO
//Сделать аналогичный процесс создания юзера
//Добавлять юзеров к ивенту
//Удаление юзера из ивента самостоятельно
//Удаление юзера из ивента владельцем
//Списки ивентов юзера (активные/пройденные)
//Проверка данных при записи в бд (RnD)