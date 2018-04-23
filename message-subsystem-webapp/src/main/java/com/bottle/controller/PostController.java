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
import java.util.Set;
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
        List<Post> posts = postService.getPosts( id );
        if (posts.isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        }
        return new ResponseEntity<>( posts, HttpStatus.OK );
    }

    @RequestMapping(path = "/savePost", method = RequestMethod.POST)
    public ResponseEntity<Void> savePost(@RequestBody RequestDTO requestDTO) {
        Post post = new Post();
        User user = allUserService.getUser( requestDTO.getPostDTO().getUser_id() );
        Security security = securityService.getSecurityByDescription( requestDTO.getPostDTO().getSecurity().getDescription() );
        if (post.getDate() == null)
            post.setDate( new Date() );
        post.setSecurity( security );
        post.setTitle( requestDTO.getPostDTO().getTitle() );
        post.setUser( user );
        post.setText( requestDTO.getPostDTO().getText() );
        post.setIsDeleted( false );
        postService.addPost( post );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(value = "/getUser", params = "user_Id", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@RequestParam("user_Id") UUID user_id) {
        User user = allUserService.getUser( user_id );
        return new ResponseEntity<>( user, HttpStatus.OK );
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = allUserService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        }
        return new ResponseEntity<>( users, HttpStatus.OK );
    }

    @RequestMapping(value = "/getSecurities", method = RequestMethod.GET)
    public ResponseEntity<List<Security>> listAllSecurities() {
        List<Security> securities = securityService.getSecurities();
        if (securities.isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        }
        return new ResponseEntity<>( securities, HttpStatus.OK );
    }

    @RequestMapping(value = "/deletePost", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@RequestParam("id") UUID postId) {
        Post post = postService.getPost( postId );
        if (post == null) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
        post.setIsDeleted( true );
        postService.addPost( post );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(path = "/updatePost", method = RequestMethod.POST)
    public ResponseEntity<Void> updatePost(@RequestBody RequestDTO requestDTO) {
        System.out.println( "Updating Post " );
        Post post = postService.getPost( requestDTO.getPostDTO().getId() );
        if (post == null) {
            System.out.println( "Post with id " + requestDTO.getPostDTO().getId() + " not found" );
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
        Security security = securityService.getSecurityByDescription( requestDTO.getPostDTO().getSecurity().getDescription() );
        post.setTitle( requestDTO.getPostDTO().getTitle() );
        post.setText( requestDTO.getPostDTO().getText() );
        post.setSecurity( security );
        post.setId( requestDTO.getPostDTO().getId() );
        postService.addPost( post );
        return new ResponseEntity<>( HttpStatus.OK );
    }
    @RequestMapping(path = "/saveComment", method = RequestMethod.POST)
    public ResponseEntity<Void> saveComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment();
        User user = allUserService.getUser(commentDTO.getUser_id() );
        Post post = postService.getPost( commentDTO.getPost_id() );
        if (comment.getDate() == null)
            comment.setDate( new Date() );
        comment.setText(commentDTO.getComment());
        comment.setPost( post);
        comment.setUser( user );
        comment.setIsDeleted( false );
        commentService.addComment( comment );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(value = "/getComments", params = "post_Id", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> listAllComments(@RequestParam("post_Id") UUID postId) {
        List<Comment> comments = commentService.getComments(postId);
        if (comments.isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        }
        return new ResponseEntity<>( comments, HttpStatus.OK );
    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteComment(@RequestParam("id") UUID commentId) {
        Comment comment = commentService.getCommentById(commentId);
        if (comment == null) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
        comment.setIsDeleted( true );
        commentService.addComment(comment);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<Void> saveUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName( userDTO.getName() );
        user.setSurname( userDTO.getSurname() );
        allUserService.createUser( user );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(path = "/addLike", method=RequestMethod.POST)
    public ResponseEntity<Void> addLike(@RequestBody LikeDTO likeDTO) {
        Post post = postService.getPost( likeDTO.getPost_id() );
        User user = allUserService.getUser( likeDTO.getUser_id() );
        Set<Like> likes;
        likes = likeService.findByPost_Id( likeDTO.getPost_id() );
        boolean a = false;
        if (likes.size() == 0) {
            Like like = new Like();
            like.setStatus( likeDTO.getStatus() );
            like.setPost( post );
            like.setUser( user );
            likeService.save( like );
            Integer size = likes.size();
            return new ResponseEntity<>( HttpStatus.OK );
        } else {
            for (Like like : likes) {
                if (like.getUser() == user) {
                    if (like.getPost() == post) {
                        System.out.println( "Вы уже оценили этот пост" );
                        System.out.println( "Ваша оценка этого поста " + like.getStatus() );
                    }
                    System.out.println( "Всего оценок" + likes.size() );
                    a = false;
                } else a = true;
            }
            if (a) {

                Like like = new Like();
                like.setStatus( likeDTO.getStatus() );
                like.setPost( post );
                like.setUser( user );
                likeService.save( like );
                System.out.println( "Ваша оценка добавлена" );
                System.out.println( "Всего оценок" + likes.size() );
                Integer size = likes.size();
                return new ResponseEntity<>( HttpStatus.OK );
            } else {

                Integer size = likes.size();
                return new ResponseEntity<>( HttpStatus.NO_CONTENT );
            }
        }
    }
}

