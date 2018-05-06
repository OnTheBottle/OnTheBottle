package com.bottle.controller;

import com.bottle.model.repository.LikeRepository;
import com.bottle.model.repository.UserRepository;
import com.bottle.service.auth.AuthService;
import com.bottle.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/news")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NewsController {
    private final NewsService newsService;
    private final AuthService authService;


    @Autowired
    public NewsController(AuthService authService, NewsService newsService) {
        this.newsService = newsService;
        this.authService = authService;
    }

    @RequestMapping(path = "/get_friends_posts", method = RequestMethod.POST)
    public List getFriendsPosts(@RequestParam(name = "id") UUID userId) {
        return newsService.getFriendsPosts(userId);
    }

    @RequestMapping(path = "/get_user_posts", method = RequestMethod.POST)
    public List getFriendsPosts(
            @RequestParam(name = "id") UUID userId,
            @RequestParam(name = "access_token") String token
    ) {
        if (!authService.isValidToken(token)) {
            return null;
        }
        UUID authId = authService.getAuthId(token);
        return newsService.getUserPosts(authId, userId);
    }

    @GetMapping(path = "/printrequest")
    public void getAllParams(@RequestParam Map<String, String> allRequestParams) {
        System.out.println(allRequestParams);
    }

}
