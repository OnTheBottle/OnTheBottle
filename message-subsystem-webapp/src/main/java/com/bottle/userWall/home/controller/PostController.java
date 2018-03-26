package com.bottle.userWall.home.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.dto.ResponseDto;
import com.home.entity.*;
import com.home.model.CommentModel;
import com.home.model.ImageModel;
import com.home.model.LikeModel;
import com.home.model.PostModel;
import com.home.repository.LikeRepository;
import com.home.repository.PostRepository;
import com.home.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.UUID;


@Controller
@RequestMapping("/")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;
    @Autowired
    SecurityService securityService;
    @Autowired
    ImageUserService imageUserService;
    @Autowired
    ImageService imageService;
    @Autowired
    CommentService commentService;
    @Autowired
    LikeService likeService;
    @Autowired
    LikeRepository likeRepository;

    @RequestMapping(value = {"/doPost"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto addPost(@RequestBody String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PostModel postModel = mapper.readValue( jsonString, PostModel.class );
        UUID post_id = UUID.fromString( postModel.getPost_id() );
        UUID user_id = UUID.fromString( postModel.getUser_id() );
        Integer security_id = Integer.parseInt( postModel.getSecurity_id() );
        Post post = new Post();
        post.setId( post_id );
        post.setTitle( postModel.getTitle() );
        post.setText( postModel.getPost() );
        if (post.getDate() == null)
            post.setDate( new Date() );

        User user = userService.getUser( user_id );
        Security security = securityService.getSecurity( security_id );

        post.setSecurity( security );
        post.setUser( user );
        postService.addPost( post );
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        return res;
    }

    @RequestMapping(value = {"/file"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto addImage(@RequestBody String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ImageModel imageModel = mapper.readValue( jsonString, ImageModel.class );
        String name = imageModel.getName();
        UUID image_id = UUID.fromString( imageModel.getImage_id() );
        UUID post_id = UUID.fromString( imageModel.getPost_id() );
        Post post = postService.getPost( post_id );
        String path = "/images/postsFiles/" + name;
        Image image = new Image();
        image.setId( image_id );
        image.setPath( path );
        image.setPost( post );
        imageService.addImage( image );
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        return res;
    }

    @RequestMapping(value = {"/getSelect"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody

    public ResponseDto getSelect() {
        Iterable<Security> securities;
        securities = securityService.getSecurities();
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        res.setData( securities );
        return res;
    }

    @RequestMapping(value = {"/getPosts"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto showposts(@RequestBody UUID user_id) {
        Iterable<Post> posts;
        posts = postService.getFirst10Post( user_id );
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        res.setData( posts );
        return res;
    }

    @RequestMapping(value = {"/getUsers"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto getUsers() {
        Iterable<User> users;
        users = userService.findAll();
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        res.setData( users );
        return res;
    }

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto addUser(@RequestBody User user) {
        userService.addUser( user );
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        return res;
    }

    @RequestMapping(value = {"/getImage"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto getImage(@RequestBody UUID user_id) {
        ImageUser imageUser = imageUserService.getImageByUserId( user_id );
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        res.setData( imageUser );
        return res;
    }

    @RequestMapping(value = {"/getPostImage"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto getPostImage(@RequestBody UUID post_id) {
        Image imagePost = imageService.getImageByPostId( post_id );
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        res.setData( imagePost );
        return res;
    }

    @RequestMapping(value = {"/deletePost"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto deletePost(@RequestBody UUID post_id) {
        postRepository.delete( post_id );
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        return res;
    }

    @RequestMapping(value = {"/showComments"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto showComments(@RequestBody UUID post_id) {
        Iterable<Comment> comments;
        comments = commentService.getComments( post_id );
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        res.setData( comments );
        return res;
    }

    @RequestMapping(value = {"/сomment"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto addComment(@RequestBody String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CommentModel commentModel = mapper.readValue( jsonString, CommentModel.class );
        UUID post_id = UUID.fromString( commentModel.getPost_id() );
        UUID user_id = UUID.fromString( commentModel.getUser_id() );
        Comment comment = new Comment();
        comment.setText( commentModel.getComment() );
        if (comment.getDate() == null)
            comment.setDate( new Date() );
        User user = userService.getUser( user_id );
        Post post = postService.getPost( post_id );
        comment.setPost( post );
        comment.setUser( user );
        commentService.addComment( comment );
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        return res;
    }

    @RequestMapping(value = {"/getPost"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto showpost(@RequestBody UUID post_id) {
        Post post;
        post = postService.getPost( post_id );
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        res.setData( post );
        return res;
    }

    @RequestMapping(value = {"/deleteComment"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto deleteComment(@RequestBody UUID comment_id) {
        commentService.deleteCommentById( comment_id );
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        return res;
    }

    @RequestMapping(value = {"/addLike"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto addLike(@RequestBody String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        LikeModel likeModel = mapper.readValue( jsonString, LikeModel.class );
        String status = likeModel.getStatus();
        UUID post_id = likeModel.getPost_id();
        UUID user_id = likeModel.getUser_id();
        Post post = postRepository.findOne( post_id );
        User user = userService.getUser( user_id );
        Set<Like> likes;
        likes = likeRepository.findByPost_Id( post_id );
        boolean a = false;
        if(likes.size()==0){Like like = new Like();
            like.setStatus( status );
            like.setPost( post );
            like.setUser( user );
            likeService.save( like );
            ResponseDto res = new ResponseDto();
            res.setStatus( "Done" );
            Integer size=likes.size();
            res.setData( size );
            return res;}
            else{
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
        if (a == true) {

            Like like = new Like();
            like.setStatus( status );
            like.setPost( post );
            like.setUser( user );
            likeService.save( like );
            System.out.println( "Ваша оценка добавлена" );
            System.out.println( "Всего оценок" + likes.size() );
            ResponseDto res = new ResponseDto();
            res.setStatus( "Done" );
            Integer size=likes.size();
            res.setData( size );
            return res;
        } else {
            ResponseDto res = new ResponseDto();
            res.setStatus( "No" );
            Integer size=likes.size();
            res.setData( size );
            return res;
        }}
    }

    @RequestMapping(value = {"/showRegards"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseDto showRegards(@RequestBody UUID post_id) {
        Iterable<Like> likes;
        likes = likeRepository.findAllByPost_Id( post_id );
        ResponseDto res = new ResponseDto();
        res.setStatus( "Done" );
        res.setData( likes );
        return res;
    }




}




