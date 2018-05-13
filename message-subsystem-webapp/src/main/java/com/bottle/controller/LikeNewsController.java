package com.bottle.controller;

import com.bottle.service.auth.AuthService;
import com.bottle.service.news.LikeNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/news/like")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LikeNewsController {

    private final LikeNewsService likeService;
    private final AuthService authService;

    @Autowired
    public LikeNewsController(LikeNewsService likeService, AuthService authService) {
        this.likeService = likeService;
        this.authService = authService;
    }

    @RequestMapping(path = "/change_like", method = RequestMethod.POST)
    public int changeStateLike(
            @RequestParam(name = "access_token") String token,
            @RequestParam(name = "postId") UUID postId) {
        return changeState(token,postId,"like"); //-1 removed, 1 - added
    }

    @RequestMapping(path = "/change_dislike", method = RequestMethod.POST)
    public int changeStateDisLike(
            @RequestParam(name = "access_token") String token,
            @RequestParam(name = "postId") UUID postId) {
        return changeState(token,postId,"dislike"); //-1 removed, 1 - added
    }

    private int changeState(String token, UUID postId, String clickBy) {
        if (authService.isValidToken(token)) {
            UUID authId = authService.getAuthId(token);
            return likeService.changeState(postId, authId, clickBy);
        }
        return 0;
    }
}
