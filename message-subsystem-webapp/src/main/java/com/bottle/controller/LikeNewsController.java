package com.bottle.controller;

import com.bottle.service.news.LikeNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/news/like")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LikeNewsController {

    private LikeNewsService likeService;

    @Autowired
    public LikeNewsController(LikeNewsService likeService) {
        this.likeService = likeService;
    }

    @RequestMapping(path = "/change", method = RequestMethod.POST)
    public boolean changeState(
            @RequestParam(name = "userId") UUID userId,
            @RequestParam(name = "postId") UUID postId) {
        return likeService.changeState(postId, userId);
    }

}
