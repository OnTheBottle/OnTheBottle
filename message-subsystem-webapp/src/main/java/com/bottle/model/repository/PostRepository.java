package com.bottle.model.repository;

import com.bottle.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    Post findById(UUID post_id);

    List<Post> findAllByUserId(UUID user_id);

    List<Post> findFirst10ByUserIdOrderByDateDesc(UUID user_id);
}
