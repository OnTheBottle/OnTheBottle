package com.bottle.temp.news.repository;

import com.bottle.temp.news.entity.FavoritePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FavoritePostRepository extends JpaRepository<FavoritePost, UUID> {
    List<FavoritePost> getFavoritePostByPostIdAndUserId(UUID postId, UUID userId);
}
