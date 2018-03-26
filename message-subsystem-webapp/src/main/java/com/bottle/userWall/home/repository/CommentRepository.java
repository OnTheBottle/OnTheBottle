package com.bottle.userWall.home.repository;

import com.home.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<Comment,UUID> {
    Iterable<Comment> findByPostId(UUID post_id);
}