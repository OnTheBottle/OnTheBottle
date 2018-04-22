package com.bottle.pubs.places.repository;

import com.bottle.pubs.places.entity.Comment;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.UUID;

public interface PubsCommentsRepository extends CrudRepository<Comment, UUID> {

    List<Comment> getCommentsByPlace_id(UUID place_id);

    Comment save(Comment comment);
}
