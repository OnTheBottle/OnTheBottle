package com.bottle.model.repository;

import com.bottle.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByIsDeletedIsFalseAndUserId(UUID user_id);

    List<Post> findAllByIsDeletedIsFalseAndUserIdAndSecurityIdLessThan(UUID user_id, int securityLevel);

    boolean existsByIdAndFavoriteUsersContaining(UUID postId, User user);
    //boolean existsByIdAndLikeUsersContaining(UUID postId, User user);

    List<Post> findAllByIsDeletedIsFalseAndUserIdAndSecurity_Name(UUID user_id, String SecurityName);

    List<Post> findAllByUserIdOrSaversContainingAndIsDeletedFalseOrderByDateDesc(UUID userId, Saver saver);
}
