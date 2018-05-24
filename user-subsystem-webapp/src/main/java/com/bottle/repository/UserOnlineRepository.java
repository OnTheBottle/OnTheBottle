package com.bottle.repository;

import com.bottle.entity.UserOnline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserOnlineRepository extends JpaRepository<UserOnline, UUID> {

    //boolean existsByUserIdAndOnline(UUID userId, boolean status);
    //boolean existsByUserId(UUID userId);

    //UserOnline getByUserId(UUID userId);
}
