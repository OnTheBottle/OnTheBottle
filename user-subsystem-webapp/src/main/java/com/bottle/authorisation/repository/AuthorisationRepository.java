package com.bottle.authorisation.repository;

import сom.bottle.authorisation.entity.User;

import java.util.UUID;

public interface AuthorisationRepository extends CrudRepository<User, UUID> {
    User findByEmail(String email);
    User findByLogin(String login);
    void addUser(User user);

}
