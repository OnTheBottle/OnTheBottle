package com.bottle.temp.news;

import com.bottle.temp.news.client.NewsClient;
import com.bottle.temp.news.dto.FriendDTO;
import com.bottle.temp.news.entity.Comment;
import com.bottle.temp.news.entity.Post;
import com.bottle.temp.news.repository.PostRepository;
import com.bottle.temp.news.repository.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
//@EnableAutoConfiguration
//@EnableJpaRepositories("com.bottle")
//@ComponentScan("com.bottle")
//@EntityScan("com.bottle")
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
        System.out.println("Start Application");


        NewsClient client = new NewsClient();
        List<FriendDTO> friends = client.getFriends(UUID.randomUUID());
        //System.out.println("Friend by ID: " + friends);

    }
}
