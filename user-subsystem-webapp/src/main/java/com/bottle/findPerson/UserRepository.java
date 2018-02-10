package com.bottle.findPerson;

import java.util.List;

public interface UserRepository extends CrudRepository {
    List<User> getByName(String name);
    List<User> getBySurname(String surname);
    List<User> getByCity(String city);
    List<User> getByCountry(String country);
    List<User> getByAge(int age);

}
