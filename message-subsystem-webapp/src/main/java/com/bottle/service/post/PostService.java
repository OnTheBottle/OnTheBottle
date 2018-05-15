package com.bottle.service.post;


import com.bottle.model.entity.Post;
import com.bottle.model.entity.Saver;
import com.bottle.model.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public void addPost(Post post) {
        postRepository.save( post );
    }

    @Transactional
    public void updatePost(Post post) {
        postRepository.save( post );
    }

    @Transactional
    public Post getPost(UUID post_id) {
        return postRepository.findById( post_id );
    }

    @Transactional
    public void deletePostById(UUID post_id) {
        postRepository.delete( post_id );
    }

    @Transactional
    public List<Post> getPosts(UUID user_id) {
        return postRepository.findAllByIsDeletedIsFalseAndUserId( user_id );
    }

    @Transactional
    public List<Post> getFirst10Post(UUID user_id) {
        return postRepository.findFirst10ByUserIdOrderByDateDesc( user_id );
    }

    @Transactional
    public List<Post> getPostsFriend (UUID user_id,String name) {
        return postRepository.findAllByIsDeletedIsFalseAndUserIdAndSecurity_Name( user_id ,name);
    }

    @Transactional
    public List<Post> getPostsForWall(UUID userId, Saver saver ){
        return postRepository.findAllByUserIdOrSaversContainingAndIsDeletedFalse( userId,saver );
    }

}