package com.bottle.findPerson.logic;

import com.bottle.findPerson.entity.User;
import com.bottle.findPerson.entity.UserDTO;
import com.bottle.findPerson.repository.UserRepository;
import com.bottle.findPerson.request.Request;
import com.bottle.findPerson.response.Response;

import java.util.ArrayList;
import java.util.List;

public class FindPerson {

    public Response findFromDB(UserRepository repository, Request request) {
        Response response = new Response();
        String searchQuery = request.getSearchQuery();
        String type = request.getSearchType();
        List<User> userList = new ArrayList<>();
        List<UserDTO> dtoList = new ArrayList<>();
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
        for (User user : userList){
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setAge(user.getAge());
            dto.setAvatarUrl(user.getAvatar_url());
            dto.setCity(user.getCity());
            dto.setCountry(user.getCountry());
            dto.setName(user.getName());
            dto.setSurname(user.getSurname());
            dtoList.add(dto);
        }
        response.setListOfPersons(dtoList);
        return response;
    }
}
