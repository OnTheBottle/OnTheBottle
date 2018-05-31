package com.bottle.model.repository;

import com.bottle.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, UUID> {

    List<Like> findAllByPost_Id(UUID post_id);

    boolean existsByPost_IdAndUser_Id(UUID post_id, UUID user_id);

    Like getByPostAndUser(Post post, User user);
}
