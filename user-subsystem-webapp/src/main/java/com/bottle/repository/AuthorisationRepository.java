package com.bottle.repository;

import com.bottle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorisationRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    User findByLogin(String login);

}
