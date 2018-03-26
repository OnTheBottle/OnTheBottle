package com.bottle.userWall.home.repository;

import com.home.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User,UUID> {
    User findById(UUID user_id);
    User findByUsername(String name);
}
