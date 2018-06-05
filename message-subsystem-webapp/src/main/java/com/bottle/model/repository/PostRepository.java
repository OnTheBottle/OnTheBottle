package com.bottle.model.repository;

import com.bottle.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByIsDeletedIsFalseAndUserId(UUID userId);

    List<Post> findAllByIsDeletedIsFalseAndUserIdAndSecurityIdLessThan(UUID userId, int securityLevel);

    boolean existsByIdAndFavoriteUsersContaining(UUID postId, User user);
    //boolean existsByIdAndLikeUsersContaining(UUID postId, User user);

    List<Post> findAllByIsDeletedIsFalseAndUserIdAndSecurityName(UUID userId, String SecurityName);

    List<Post> findFirst5ByIsDeletedIsFalseAndUserIdOrSaversContainingOrderByDateDesc(UUID userId, Saver saver);

    List<Post> findAllByIsDeletedIsFalseAndUserIdOrSaversContainingOrderByDateDesc(UUID userId, Saver saver);




}
