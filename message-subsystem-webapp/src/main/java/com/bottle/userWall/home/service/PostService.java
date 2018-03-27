package com.bottle.userWall.home.service;


import com.bottle.userWall.home.entity.Post;
import com.bottle.userWall.home.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Transactional
    public void addPost(Post post) {
        postRepository.save( post );
    }

    @Transactional
    public void deleteAll() {
        postRepository.deleteAll();
    }

    @Transactional
    public Iterable<Post> getPosts() {return  postRepository.findAll();}

    @Transactional
    public Iterable<Post> getPostsByUserId(UUID user_id) {
        return postRepository.findAllByUserId( user_id);
    }

    @Transactional
    public void updatePost(Post post) {
        postRepository.save( post );
    }

    @Transactional
    public void deletePostById (UUID post_id){
        postRepository.delete(post_id);
    }

    @Transactional
    public Post getPost(UUID post_id){
        return postRepository.findById(post_id);
    }

    @Transactional
    public Iterable<Post> getFirst10Post(UUID user_id){return postRepository.findFirst10ByUserIdOrderByDateDesc(user_id);}

}