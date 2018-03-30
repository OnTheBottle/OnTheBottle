package com.bottle.service.post;


import com.bottle.model.entity.ImageUser;
import com.bottle.model.repository.ImageUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class ImageUserService {

    private ImageUserRepository imageUserRepository;

    @Autowired
    public ImageUserService(ImageUserRepository imageUserRepository) {
        this.imageUserRepository = imageUserRepository;
    }

    @Transactional
    public ImageUser getImageByUserId(UUID user_id) {
        return imageUserRepository.findByUserId( user_id );
    }
}

