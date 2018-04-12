package com.bottle.temp.news.repository;

import com.bottle.temp.news.entity.LikePost;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface LikePostRepository extends CrudRepository<LikePost,UUID>{
    ArrayList<LikePost> getAllByPostIdAndUserId(UUID postId, UUID userId);
    ArrayList<LikePost> getLikePostByPostId(UUID likeId);
    //ArrayList<LikePost> getLikePostByPostId(UUID postId);
}
