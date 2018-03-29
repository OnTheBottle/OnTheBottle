package com.bottle.event.service.post;

import com.bottle.event.model.entity.Image;
import com.bottle.event.model.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class ImageService {

   private ImageRepository imageRepository;

   @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Transactional
    public Image getImageByPostId(UUID post_id) {
        return imageRepository.findByPostId( post_id );
    }

    @Transactional
    public void addImage(Image image) {
        imageRepository.save( image );
    }


}