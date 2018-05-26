package com.bottle.service;

import com.bottle.entity.User;
import com.bottle.model.dto.UserDTO;
import com.bottle.repository.UserRepository;
import com.bottle.model.dto.request.FindPersonRequest;
import com.bottle.model.dto.response.FindPersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FindPersonService {

    private final UserRepository userRepository;

    @Autowired
    public FindPersonService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public FindPersonResponse findFromDB(FindPersonRequest request) {
        FindPersonResponse response = new FindPersonResponse();
//        String searchQuery = request.getSearch();
//        String type = request.getSearchType();
        List<User> userList;
        List<UserDTO> dtoList = new ArrayList<>();
        // TODO: 24.04.2018 use ENUM
//        switch (type) {
//            case "name":
//                userList = repository.getByName(searchQuery);
//                break;
//            case "surname":
//                userList = repository.getBySurname(searchQuery);
//                break;
//            case "city":
//                userList = repository.getByCity(searchQuery);
//                break;
//            case "country":
//                userList = repository.getByCountry(searchQuery);
//                break;
//            case "age":
//                userList = repository.getByAge(Integer.parseInt(searchQuery));
//                break;
//        }

        userList = userRepository.getAllUsersLike("%" + request.getSearch().toLowerCase() + "%");

// TODO: 24.04.2018 write build method
        for (User user : userList) {
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
        response.setListOfPersons(dtoList);
        return response;
    }
}
