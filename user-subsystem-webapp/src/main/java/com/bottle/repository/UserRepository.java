package com.bottle.repository;

import com.bottle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    default boolean checkAuth(String login, String pass) {
        return existsByLoginAndPasswordAndDeletedFalse(login, pass);
    }

    boolean existsByLoginAndPasswordAndDeletedFalse(String login, String pass);

    List<User> getByName(String name);

    List<User> getBySurname(String surname);

    List<User> getByCity(String city);

    List<User> getByCountry(String country);

    List<User> getByAge(int age);

    boolean existsById(UUID id);

    User getUserById(UUID id);

    User findByEmail(String email);

    User findByLogin(String login);

    Set<User> getAllByDeletedFalse();
}
