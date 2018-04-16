package com.bottle.logic;

import com.bottle.entity.User;
import com.bottle.entity.UserDTO;
import com.bottle.repository.UserRepository;
import com.bottle.request.FindPersonRequest;
import com.bottle.response.FindPersonResponse;

import java.util.ArrayList;
import java.util.List;

public class FindPerson {

    public FindPersonResponse findFromDB(UserRepository repository, FindPersonRequest request) {
        FindPersonResponse response = new FindPersonResponse();
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
