package com.bottle.userWall.home.repository;

import com.home.entity.ImageUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface ImageUserRepository extends CrudRepository<ImageUser,UUID> {
    ImageUser findByUserId(UUID user_id);

  }