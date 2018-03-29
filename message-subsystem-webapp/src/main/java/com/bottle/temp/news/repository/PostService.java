package com.bottle.temp.news.repository;

import com.bottle.temp.news.client.NewsClient;
import com.bottle.temp.news.controller.NewsController;
import com.bottle.temp.news.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Transactional
    public void add(Post post) {
        postRepository.save(post);
        System.out.println(post);
    }
}
