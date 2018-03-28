package com.bottle.userWall.home.repository;

import com.bottle.userWall.home.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends CrudRepository<User,UUID> { //TODO +"s"
    User findById(UUID user_id);
    User findByUsername(String name);
}
