package com.bottle.event.model.repository;

import com.bottle.event.model.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<Comment, UUID> {
    Iterable<Comment> findByPostId(UUID post_id);
}