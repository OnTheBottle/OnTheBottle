package com.bottle.event.model.repository;

import com.bottle.event.model.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends CrudRepository<Post, UUID> {
    Post findById(UUID post_id);

    Iterable<Post> findAllByUserId(UUID user_id);

    Iterable<Post> findFirst10ByUserIdOrderByDateDesc(UUID user_id);

}
