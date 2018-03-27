package com.bottle.userWall.home.service;

import com.bottle.userWall.home.entity.Image;
import com.bottle.userWall.home.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Transactional
    public Image getImageByPostId(UUID post_id) {
        return imageRepository.findByPostId(post_id);
    }
    @Transactional
    public void addImage(Image image) {
        imageRepository.save(image );
    }


}