package com.bottle.userWall.home.service;


import com.bottle.userWall.home.entity.ImageUser;
import com.bottle.userWall.home.repository.ImageUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class ImageUserService {
    @Autowired
    ImageUserRepository imageUserRepository;
    @Transactional
    public ImageUser getImageByUserId(UUID user_id) {
        return imageUserRepository.findByUserId(user_id);
    }


}

