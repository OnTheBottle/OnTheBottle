package com.bottle.logic;

import com.bottle.entity.User;
import com.bottle.model.dto.UserDTO;
import com.bottle.repository.UserRepository;
import com.bottle.model.dto.request.FindPersonRequest;
import com.bottle.model.dto.responce.FindPersonResponse;

import java.util.ArrayList;
import java.util.List;

public class FindPerson {

    public FindPersonResponse findFromDB(UserRepository repository, FindPersonRequest request) {
        FindPersonResponse response = new FindPersonResponse();
        String searchQuery = request.getSearch();
        String type = request.getSearchType();
        List<User> userList = new ArrayList<>();
        List<UserDTO> dtoList = new ArrayList<>();
        // TODO: 24.04.2018 use ENUM
        switch (type) {
            case "name":
                userList = repository.getByName(searchQuery);
                break;
            case "surname":
                userList = repository.getBySurname(searchQuery);
                break;
            case "city":
                userList = repository.getByCity(searchQuery);
                break;
            case "country":
                userList = repository.getByCountry(searchQuery);
                break;
            case "age":
                userList = repository.getByAge(Integer.parseInt(searchQuery));
                break;

        }

        System.out.println("User's name from response is " + userList.get(0).getName());
// TODO: 24.04.2018 write build method
        for (User user : userList){
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setAge(user.getAge());
            dto.setAvatarUrl(user.getAvatarUrl());
            dto.setCity(user.getCity());
            dto.setCountry(user.getCountry());
            dto.setName(user.getName());
            dto.setSurname(user.getSurname());
            dtoList.add(dto);
        }
        System.out.println("DtoUser's name from response is " + dtoList.get(0).getName());
        response.setListOfPersons(dtoList);
        return response;
    }
}
