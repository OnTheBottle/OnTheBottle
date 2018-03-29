package com.bottle.model.repository;

import com.bottle.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findById(UUID user_id);
    User findByName(String name);
}
