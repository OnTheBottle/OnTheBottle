package com.bottle.userWall.home.service;

import com.bottle.userWall.home.entity.Like;
import com.bottle.userWall.home.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;

    @Transactional
    public void save(Like like) {
        likeRepository.save( like );
    }



}
