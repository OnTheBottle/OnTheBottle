package com.bottle.service.post;

import com.bottle.model.entity.Like;
import com.bottle.model.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;


@Service
public class LikeService {

    private LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Transactional
    public void save(Like like) {
        likeRepository.save( like );
    }

    @Transactional
    public HashSet<Like> findByPost_Id(UUID post_id) {
        return likeRepository.findByPost_Id( post_id );
    }

    @Transactional
    public List<Like> findByPostId(UUID postId) {
        return likeRepository.findAllByPost_Id( postId );
    }

    @Transactional
    public boolean exists(UUID postId,UUID userId){return likeRepository.existsByPost_IdAndUser_Id( postId,userId );}
}
