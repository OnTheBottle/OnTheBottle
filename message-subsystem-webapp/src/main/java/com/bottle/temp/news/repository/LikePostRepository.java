package com.bottle.temp.news.repository;

import com.bottle.temp.news.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface LikePostRepository extends JpaRepository<LikePost, UUID> {
    ArrayList<LikePost> findAllByPostIdAndUserId(UUID postId, UUID userId);
}
