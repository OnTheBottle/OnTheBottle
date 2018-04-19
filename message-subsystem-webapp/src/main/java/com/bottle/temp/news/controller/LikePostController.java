package com.bottle.temp.news.controller;

import com.bottle.temp.news.entity.LikePost;
import com.bottle.temp.news.repository.LikePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/news/like")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LikePostController {
    private LikePostRepository repository;

    @Autowired
    public LikePostController(LikePostRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "/change", method = RequestMethod.POST)
    public void change(
            @RequestParam(name = "userId") UUID userId,
            @RequestParam(name = "postId") UUID postId) {
        System.out.println("request: " + userId + " " + postId);
        List<LikePost> likes = repository.findAllByPostIdAndUserId(postId, userId);
        if (likes.size() > 0) {
            repository.delete(likes);
            System.out.println("DELETE Like: " + userId + "  " + postId);
        } else {
            LikePost post = new LikePost();
            post.setLikeId(UUID.randomUUID());
            post.setPostId(postId);
            post.setUserId(userId);
            post.setDate(new Date());
            repository.save(post);
            System.out.println("ADD new Like: " + post.getLikeId());
        }
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public void add() {
        UUID userId;
        UUID postId;
        Date date;
        //Test Data
        userId = UUID.fromString("36fe8f70-3287-4521-b00f-807682ab8204");
        postId = UUID.fromString("bfae92d5-84c1-46ec-afe4-57a44bfac85e");
        date = new Date();

        LikePost like = new LikePost();
        like.setUserId(userId);
        like.setPostId(postId);
        like.setDate(date);
        System.out.println(like);
        repository.save(like);
    }

}
