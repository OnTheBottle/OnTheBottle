package com.bottle.event.model.repository;

import com.bottle.event.model.entity.ImageUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface ImageUserRepository extends CrudRepository<ImageUser, UUID> {
    ImageUser findByUserId(UUID user_id);

}