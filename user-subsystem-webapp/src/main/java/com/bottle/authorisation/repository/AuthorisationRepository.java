package com.bottle.authorisation.repository;

import com.bottle.authorisation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorisationRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    User findByLogin(String login);
    void addUser(User user);

}
