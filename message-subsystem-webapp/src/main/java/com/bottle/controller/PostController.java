package com.bottle.controller;


import com.bottle.model.DTO.*;
import com.bottle.model.entity.*;
import com.bottle.service.post.*;
import com.bottle.service.user.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
public class PostController {
    private PostService postService;
    private AllUserService allUserService;
    private SecurityService securityService;
    private ImageService imageService;
    private CommentService commentService;
    private LikeService likeService;

    @Autowired
    public PostController(PostService postService, AllUserService allUserService, SecurityService securityService, ImageService imageService, CommentService commentService, LikeService likeService) {
        this.postService = postService;
        this.allUserService = allUserService;
        this.securityService = securityService;
        this.imageService = imageService;
        this.commentService = commentService;
        this.likeService = likeService;
    }

    @RequestMapping(value = "/getPosts", params = "user_Id", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> listAllPosts(@RequestParam("user_Id") UUID id) {
        List<Post> posts = postService.getPosts(id);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @RequestMapping(value = "/createPost", params = "post", method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@RequestParam("post") PostDTO postDTO) {
        Post post = new Post();
        User user = allUserService.getUser(postDTO.getUser_id());
        Security security = securityService.getSecurity(postDTO.getSecurity_id());
        if (post.getDate() == null)
            post.setDate(new Date());
        post.setSecurity(security);
        post.setTitle(postDTO.getTitle());
        post.setUser(user);
        post.setText(postDTO.getText());
        postService.addPost(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @RequestMapping(value = "/getUser", params = "user_Id", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@RequestParam("user_Id") UUID user_id) {
        User user = allUserService.getUser(user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = allUserService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/getSecurities", method = RequestMethod.GET)
    public ResponseEntity<List<Security>> listAllSecurities() {
        List<Security> securities = securityService.getSecurities();
        if (securities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(securities, HttpStatus.OK);
    }

    @RequestMapping(value = "/deletePost", method = RequestMethod.DELETE)
    public ResponseEntity<Post> deletePost(@RequestParam("post_id") UUID post_id) {
        System.out.println("Fetching & Deleting Post with id " + post_id);

        Post post = postService.getPost(post_id);
        if (post == null) {
            System.out.println("Unable to delete. Post with id " + post_id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        postService.deletePostById(post_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    public ResponseEntity<Post> updatePost(@RequestParam("post") PostDTO postDto) {
        System.out.println("Updating Post ");

        Post post = postService.getPost(postDto.getPost_id());

        if (post == null) {
            System.out.println("Post with id " + postDto.getPost_id() + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Security security = securityService.getSecurity(postDto.getSecurity_id());
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setSecurity(security);
        post.setId(postDto.getPost_id());
        postService.addPost(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}

