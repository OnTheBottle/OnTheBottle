package com.bottle.service.post;

import com.bottle.model.entity.Like;
import com.bottle.model.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


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


}
