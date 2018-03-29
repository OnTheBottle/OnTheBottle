package com.bottle.event.service.post;

import com.bottle.event.model.entity.Like;
import com.bottle.event.model.repository.LikeRepository;
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
