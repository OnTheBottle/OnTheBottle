package com.bottle.model.repository;

import com.bottle.model.entity.FavoriteNews;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface FavoriteNewsRepository extends CrudRepository<FavoriteNews, UUID> {
    ArrayList<FavoriteNews> getFavoritePostByPostIdAndUserId(UUID postId, UUID userId);
}
