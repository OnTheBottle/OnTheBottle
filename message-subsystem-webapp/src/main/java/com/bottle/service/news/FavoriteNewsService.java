package com.bottle.service.news;

import com.bottle.model.entity.Post;
import com.bottle.model.entity.User;
import com.bottle.model.repository.PostRepository;
import com.bottle.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FavoriteNewsService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public FavoriteNewsService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public boolean changeState(UUID postId, UUID userId) {
        User user = userRepository.findOne(userId);
        Post post = postRepository.findOne(postId);
        boolean isExist = postRepository.existsByIdAndFavoriteUsersContaining(postId, user);
        if (isExist) {
            post.getFavoriteUsers().remove(user);
            postRepository.save(post);
            return false;
        }
        post.getFavoriteUsers().add(user);
        postRepository.save(post);
        return true;
    }
}
