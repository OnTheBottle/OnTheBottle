package com.bottle.controller;


import com.bottle.model.DTO.*;
import com.bottle.model.entity.*;
import com.bottle.service.post.*;
import com.bottle.service.user.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class PostController {
    private AllPostService allPostService;
    private AllUserService allUserService;
    private AllCommentService allCommentService;
    private AllLikeService allLikeService;
    private SecurityService securityService;
    private ImageService imageService;

    @Autowired
    public PostController(AllPostService allPostService, AllUserService allUserService, SecurityService securityService, ImageService imageService, AllCommentService allCommentService, AllLikeService allLikeService) {
        this.allPostService = allPostService;
        this.allUserService = allUserService;
        this.securityService = securityService;
        this.imageService = imageService;
        this.allCommentService = allCommentService;
        this.allLikeService = allLikeService;
    }

    @RequestMapping(value = "/getPosts", params = "userId", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> listAllPosts(@RequestParam("userId") UUID id) {
        return new ResponseEntity<>( allPostService.getPostsWithSaver( id ), HttpStatus.OK );
    }

    @RequestMapping(value = "/getPostsFriend", params = "userId", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> listAllPostsFriend(@RequestParam("userId") UUID id) {
        return new ResponseEntity<>( allPostService.getPostsFriend( id ), HttpStatus.OK );
    }

    @RequestMapping(path = "/savePostToMyWall", method = RequestMethod.POST)
    public ResponseEntity<Void> savePostToMyWall(@RequestBody SaverDTO saverDTO) {
        allPostService.postToMyWall( saverDTO );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(path = "/dropFromWall", method = RequestMethod.DELETE)
    public ResponseEntity<Void> dropFromWall(@RequestBody SaverDTO saverDTO) {
        allPostService.deleteFromWall( saverDTO );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(path = "/savePost", method = RequestMethod.POST)
    public ResponseEntity<Void> savePost(@RequestBody PostDTO postDTO) {
        allPostService.addPost( postDTO );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(value = "/deletePost", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@RequestParam("id") UUID postId) {
        allPostService.deletePost( postId );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(path = "/updatePost", method = RequestMethod.POST)
    public ResponseEntity<Void> updatePost(@RequestBody PostDTO postDTO) {
        allPostService.updatePost( postDTO );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        return new ResponseEntity<>( allUserService.getAllUsers(), HttpStatus.OK );
    }

    @RequestMapping(value = "/getSecurities", method = RequestMethod.GET)
    public ResponseEntity<List<Security>> listAllSecurities() {
        return new ResponseEntity<>( securityService.getSecurities(), HttpStatus.OK );
    }

    @RequestMapping(path = "/saveComment", method = RequestMethod.POST)
    public ResponseEntity<Void> saveComment(@RequestBody CommentDTO commentDTO) {
        allCommentService.saveComment( commentDTO );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(value = "/getComments", params = "postId", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> listAllComments(@RequestParam("postId") UUID postId) {
       return new ResponseEntity<>(allCommentService.getComments( postId ), HttpStatus.OK );
    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteComment(@RequestParam("id") UUID commentId) {
        allCommentService.deleteComment( commentId );
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

    @RequestMapping(path = "/addLike", method = RequestMethod.POST)
    public ResponseEntity<Void> addLike(@RequestBody LikeDTO likeDTO) {
        boolean like=allLikeService.addLike(likeDTO);
        if (like) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        } else {
            return new ResponseEntity<>( HttpStatus.OK );
        }
    }

    @RequestMapping(value = "/getLikes", params = "postId", method = RequestMethod.GET)
    public ResponseEntity<List<Like>> listAllLikes(@RequestParam("postId") UUID postId) {
        return new ResponseEntity<>(allLikeService.getLikes( postId ), HttpStatus.OK );
    }
}

