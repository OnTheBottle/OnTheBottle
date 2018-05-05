package com.bottle.controller;

import com.bottle.service.news.FavoriteNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/news/favorite")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FavoriteNewsController {

    private FavoriteNewsService favoriteService;

    @Autowired
    public FavoriteNewsController(FavoriteNewsService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @RequestMapping(path = "/change", method = RequestMethod.POST)
    public boolean changeState(
            @RequestParam(name = "userId") UUID userId,
            @RequestParam(name = "postId") UUID postId) {
        return favoriteService.changeState(postId, userId);
    }

}
