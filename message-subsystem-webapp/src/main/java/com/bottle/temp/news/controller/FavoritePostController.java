package com.bottle.temp.news.controller;

import com.bottle.temp.news.entity.FavoritePost;
import com.bottle.temp.news.entity.LikePost;
import com.bottle.temp.news.repository.FavoritePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/news/favorite")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FavoritePostController {
    @Autowired
    FavoritePostRepository favoriteRepository;

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(path = "/change", method = RequestMethod.POST)
    public void getFavorite(
            @RequestParam(name = "userId") UUID userId,
            @RequestParam(name = "postId") UUID postId,
            @RequestParam(name = "isFavorite") boolean isFavorite) {
        System.out.println("request: " + userId + " " + postId + " " + isFavorite);
        List<FavoritePost> favoritePosts = favoriteRepository.getFavoritePostByPostIdAndUserId(postId, userId);
        System.out.println(favoritePosts);
    }

}
