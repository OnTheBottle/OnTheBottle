package com.bottle.findPerson;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public class FindPerson {

    public List<User> findFromDB(UserRepository repository, Model model) {
        Map<String, Object> data=model.asMap();
        String searchQuery=(String) data.get("search");
        String type=(String) data.get("searchType");
        switch (type){
            case "name":return repository.getByName(searchQuery);
            case "surname":return repository.getBySurname(searchQuery);
            case "city":return repository.getByCity(searchQuery);
            case "country":return repository.getByCountry(searchQuery);
            case "age":return repository.getByAge(Integer.parseInt(searchQuery));
            default: return null;
        }
    }
}
