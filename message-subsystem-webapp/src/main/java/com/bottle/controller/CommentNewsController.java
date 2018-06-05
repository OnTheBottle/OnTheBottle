package com.bottle.controller;

import com.bottle.model.entity.Comment;
import com.bottle.service.auth.AuthService;
import com.bottle.service.post.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/news/comment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentNewsController {

    private final CommentService commentService;
    private final AuthService authService;

    @Autowired
    public CommentNewsController(CommentService commentService, AuthService authService) {
        this.commentService = commentService;
        this.authService = authService;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Comment add(
            @RequestParam(name = "access_token") String token,
            @RequestParam(name = "text") String text,
            @RequestParam(name = "postId") UUID postId) {
        if (authService.isValidToken(token)) {
            UUID authId = authService.getAuthId(token);
            return commentService.addComment(authId, postId, text);
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public boolean delete(
            @RequestParam(name = "access_token") String token,
            @RequestParam(name = "commentId") UUID commentId) {
        if (authService.isValidToken(token)) {
            UUID authId = authService.getAuthId(token);
            return commentService.deleteComment(authId, commentId);
        }
        return false;
    }

}
