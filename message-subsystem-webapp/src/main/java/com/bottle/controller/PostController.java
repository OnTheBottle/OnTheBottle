package com.bottle.controller;

import com.bottle.model.DTO.*;
import com.bottle.model.entity.*;
import com.bottle.service.auth.AuthService;
import com.bottle.service.post.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class PostController {
    private PostService postService;
    private CommentService commentService;
    private LikeService likeService;
    private SecurityService securityService;
    private AuthService authService;

    @Autowired
    public PostController(PostService postService, SecurityService securityService, CommentService commentService, LikeService likeService, AuthService authService) {
        this.postService = postService;
        this.securityService = securityService;
        this.commentService = commentService;
        this.likeService = likeService;
        this.likeService = likeService;
        this.authService = authService;
    }

    @RequestMapping(path = "/getMorePosts", method = RequestMethod.GET)
    public ResponseEntity<?> listMorePosts(@RequestParam("lastPostId") UUID lastPostId,
                                           @RequestParam("userId") UUID userId,
                                           @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        if (lastPostId == null) {
            return ErrorResponse.getErrorResponse( "PostId may not be null" );
        }
        return new ResponseEntity<>( postService.getMorePosts( userId, lastPostId ), HttpStatus.OK );
    }

    @RequestMapping(path = "/getPosts", method = RequestMethod.GET)
    public ResponseEntity<?> listAllPosts(@RequestParam("userId") UUID id,
                                          @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        return new ResponseEntity<>( postService.getPostsWithSaver( id ), HttpStatus.OK );
    }

    @RequestMapping(path = "/getPostsFriend", params = "userId", method = RequestMethod.GET)
    public ResponseEntity<?> listAllPostsFriend(@RequestParam("userId") UUID id,
                                                @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        return new ResponseEntity<>( postService.getPostsFriend( id ), HttpStatus.OK );
    }

    @RequestMapping(path = "/savePostToMyWall", method = RequestMethod.POST)
    public ResponseEntity<?> savePostToMyWall(@RequestParam("postId") UUID postId,
                                              @RequestParam("saverId") UUID saverId,
                                              @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        postService.postToMyWall( postId, saverId );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(path = "/dropFromWall", method = RequestMethod.DELETE)
    public ResponseEntity<?> dropFromWall(@RequestParam("postId") UUID postId,
                                          @RequestParam("saverId") UUID saverId,
                                          @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        postService.deleteFromWall( postId, saverId );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(path = "/savePost", method = RequestMethod.POST)
    public ResponseEntity<?> savePost(@RequestBody PostDTO postDTO,
                                      @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        Post post = postService.addPost( postDTO );
        return new ResponseEntity<>( post, HttpStatus.OK );
    }

    @RequestMapping(path = "/deletePost", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePost(@RequestParam("postId") UUID postId,
                                        @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        postService.deletePost( postId );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(path = "/updatePost", method = RequestMethod.POST)
    public ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO,
                                        @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        postService.updatePost( postDTO );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(path = "/getSecurities", method = RequestMethod.GET)
    public ResponseEntity<?> listAllSecurities(@RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        return new ResponseEntity<>( securityService.getSecurities(), HttpStatus.OK );
    }

    @RequestMapping(path = "/saveComment", method = RequestMethod.POST)
    public ResponseEntity<?> saveComment(@RequestBody CommentDTO commentDTO,
                                         @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        Comment comment = commentService.saveComment( commentDTO );
        return new ResponseEntity<>( comment, HttpStatus.OK );
    }

    @RequestMapping(value = "/getComments", method = RequestMethod.GET)
    public ResponseEntity<?> listAllComments(@RequestParam("postId") UUID postId,
                                             @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        return new ResponseEntity<>( commentService.getComments( postId ), HttpStatus.OK );
    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteComment(@RequestParam("commentId") UUID commentId,
                                           @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        commentService.deleteCommentFromPost( commentId );
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

    @RequestMapping(path = "/addLike", method = RequestMethod.POST)
    public ResponseEntity<?> addLike(@RequestBody LikeDTO likeDTO,
                                     @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        boolean like = likeService.addLike( likeDTO );
        if (like) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        } else {
            return new ResponseEntity<>( likeService.getLikes( likeDTO.getPostId() ), HttpStatus.OK );
        }
    }

    @RequestMapping(value = "/getLikes", method = RequestMethod.GET)
    public ResponseEntity<?> listAllLikes(@RequestParam("postId") UUID postId,
                                          @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        return new ResponseEntity<>( likeService.getLikes( postId ), HttpStatus.OK );
    }

    @RequestMapping(value = "/deleteLike", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteLike(@RequestParam("likeId") UUID likeId,
                                        @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }

        return new ResponseEntity<>( likeService.deleteLike( likeId), HttpStatus.OK );
    }
}

