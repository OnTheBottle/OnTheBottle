package com.bottle.model.repository;


import com.bottle.model.entity.Post;
import com.bottle.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface PostRepository extends JpaRepository<Post, UUID> {
    Post findById(UUID post_id);

    List<Post> findAllByUserId(UUID user_id);

    List<Post> findAllByIsDeletedIsFalseAndUserId(UUID user_id);
    List<Post> findAllByIsDeletedIsFalseAndUserIdAndSecurityIdLessThan(UUID user_id, int securityLevel);
    List<Post> findFirst10ByUserIdOrderByDateDesc(UUID user_id);

    Post getById(UUID postId);
    boolean existsByIdAndFavoriteUsersContaining(UUID postId, User user);
    //boolean existsByIdAndLikeUsersContaining(UUID postId, User user);

}
