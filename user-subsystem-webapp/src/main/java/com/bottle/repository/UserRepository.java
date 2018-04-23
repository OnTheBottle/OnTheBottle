package com.bottle.repository;

import com.bottle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> getByName(String name);
    List<User> getBySurname(String surname);
    List<User> getByCity(String city);
    List<User> getByCountry(String country);
    List<User> getByAge(int age);
    User getUserById(UUID id);
    User findByEmail(String email);
    User findByLogin(String login);

    Set<User> getAllByDeletedFalse();
}
