package com.bottle.event.model.repository;

import com.bottle.event.model.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageRepository extends CrudRepository<Image, UUID> {
    Image findByPostId(UUID post_id);
}
