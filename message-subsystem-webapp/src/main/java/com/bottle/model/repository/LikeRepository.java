package com.bottle.model.repository;

import com.bottle.model.entity.Like;
import com.bottle.model.entity.Post;
import com.bottle.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;


@Repository
public interface LikeRepository extends CrudRepository<Like, UUID> {
    @Transactional
    HashSet<Like> findByPost_Id(UUID post_id);

    @Transactional
    List<Like> findAllByPost_Id(UUID post_id);

    Like findByPost_IdAndUser_Id(UUID post_id,UUID user_id);
    boolean existsByPost_IdAndUser_Id(UUID post_id,UUID user_id);

    Like getByPostAndUser(Post post, User user);

}
