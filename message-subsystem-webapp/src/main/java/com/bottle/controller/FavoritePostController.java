package com.bottle.controller;

import com.bottle.model.entity.FavoritePost;
import com.bottle.model.repository.FavoritePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/news/favorite")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FavoritePostController {
    private final FavoritePostRepository favoriteRepository;

    @Autowired
    public FavoritePostController(FavoritePostRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @RequestMapping(path = "/change", method = RequestMethod.POST)
    public void getFavorite(
            @RequestParam(name = "userId") UUID userId,
            @RequestParam(name = "postId") UUID postId) {
        System.out.println("request: " + userId + " " + postId);
        List<FavoritePost> favoritePosts = favoriteRepository.getFavoritePostByPostIdAndUserId(postId, userId);
        System.out.println(favoritePosts);
    }

}
