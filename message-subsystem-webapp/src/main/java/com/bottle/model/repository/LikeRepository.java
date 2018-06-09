package com.bottle.model.repository;

import com.bottle.model.entity.Like;
import com.bottle.model.entity.Post;
import com.bottle.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, UUID> {

    List<Like> findAllByPostId(UUID postId);

    boolean existsByPostIdAndUserId(UUID postId, UUID userId);

    Like getByPostAndUser(Post post, User user);
}
