package com.bottle.model.repository;

import com.bottle.model.entity.FavoritePost;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface FavoritePostRepository extends CrudRepository<FavoritePost, UUID> {
    ArrayList<FavoritePost> getFavoritePostByPostIdAndUserId(UUID postId, UUID userId);
}
