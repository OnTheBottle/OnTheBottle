package com.bottle.controller;

import com.bottle.client.UserSubsystemClient;
import com.bottle.model.entity.Like;
import com.bottle.model.entity.Post;
import com.bottle.model.entity.User;
import com.bottle.model.repository.LikeRepository;
import com.bottle.model.repository.PostRepository;
import com.bottle.model.repository.UserRepository;
import com.bottle.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/news")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NewsController {
    private final NewsService newsService;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Autowired
    public NewsController(NewsService newsService, LikeRepository likeRepository, UserRepository userRepository) {
        this.newsService = newsService;
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/get_friends_posts", method = RequestMethod.POST)
    public List<List> getFriendsPosts(@RequestParam(name = "id") UUID userId) {
        return newsService.getFriendsPosts(userId);
    }

    @GetMapping(path = "/printrequest")
    public void getAllParams(@RequestParam Map<String, String> allRequestParams) {
        System.out.println(allRequestParams);
    }

}
