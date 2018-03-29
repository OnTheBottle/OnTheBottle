package com.bottle.temp.news.controller;

import com.bottle.temp.news.entity.Comment;
import com.bottle.temp.news.entity.Post;
import com.bottle.temp.news.repository.PostRepository;
import com.bottle.temp.news.repository.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/post")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public void add(@RequestParam Map request) {
        System.out.println("ADD POST.");
        postService.add(createPost());
    }

    private Post createPost() {
        //Test add new post
        Post post = new Post();
        UUID postId = UUID.randomUUID();
        UUID authorId = UUID.fromString("36fe8f70-3287-4521-b00f-807682ab8204");
        post.setId(postId);
        post.setAuthorId(authorId);
        post.setDate(new Date());
        post.setTitle("Test Tittle.");
        post.setPost("I add new post from Terminal console");

        Comment comment = new Comment();
        comment.setAuthorId(authorId);
        comment.setPost(post);
        comment.setComment("Very good job!!!!!!");

        return post;
    }
}


