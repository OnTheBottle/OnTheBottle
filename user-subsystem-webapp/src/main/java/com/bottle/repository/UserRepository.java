package com.bottle.repository;

import com.bottle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> getByName(String name);
    List<User> getBySurname(String surname);
    List<User> getByCity(String city);
    List<User> getByCountry(String country);
    List<User> getByAge(int age);

}
