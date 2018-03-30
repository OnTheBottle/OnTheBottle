package com.bottle.model.repository;

import com.bottle.model.entity.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.UUID;


@Repository
public interface LikeRepository extends CrudRepository<Like, UUID> {
    @Transactional
    HashSet<Like> findByPost_Id(UUID post_id);

    @Transactional
    Iterable<Like> findAllByPost_Id(UUID post_id);
}
