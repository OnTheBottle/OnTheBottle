package com.bottle.service.news;

import com.bottle.model.entity.Like;
import com.bottle.model.entity.Post;
import com.bottle.model.entity.User;
import com.bottle.model.repository.LikeRepository;
import com.bottle.model.repository.PostRepository;
import com.bottle.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LikeNewsService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private LikeRepository likeRepository;

    @Autowired
    public LikeNewsService(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    public int changeState(UUID postId, UUID userId, String clickBy) {
        User user = userRepository.getOne(userId);
        Post post = postRepository.getOne(postId);
        Like like = likeRepository.getByPostAndUser(post, user);
        int state;

        if (like != null) {
            if (clickBy.equals(like.getStatus())) {
                likeRepository.delete(like);
                state = -1;
            } else {
                like.setStatus(clickBy);
                likeRepository.save(like);
                state = 1;
            }
        } else {
            like = new Like();
            like.setStatus(clickBy);
            like.setPost(post);
            like.setUser(user);
            likeRepository.save(like);
            state = 1;
        }
        return state;
    }

}
