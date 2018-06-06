package com.bottle.service.post;

import com.bottle.model.DTO.LikeDTO;
import com.bottle.model.entity.*;
import com.bottle.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class LikeService {
    private LikeRepository likeRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository, PostRepository postRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public boolean addLike(LikeDTO likeDTO) {
        Post post = postRepository.findOne( likeDTO.getPostId() );
        User user = userRepository.findOne( likeDTO.getUserId() );
        boolean proverka = likeRepository.existsByPostIdAndUserId( likeDTO.getPostId(), likeDTO.getUserId() );
        if (proverka) {
            return true;
        } else {
            Like like = new Like( likeDTO.getStatus(), post, user );
            likeRepository.save( like );
            return false;
        }
    }

    @Transactional
    public List<Like> getLikes(UUID postId) {
        return likeRepository.findAllByPostId( postId );
    }

    @Transactional
    public List<Like> deleteLike(UUID likeId) {
        Like like = likeRepository.findOne( likeId );
        UUID postId = like.getPost().getId();
        likeRepository.delete( likeId );
        return likeRepository.findAllByPostId( postId );
    }
}