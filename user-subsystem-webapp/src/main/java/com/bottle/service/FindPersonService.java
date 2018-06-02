package com.bottle.service;

import com.bottle.entity.User;
import com.bottle.model.dto.UserDTO;
import com.bottle.repository.UserRepository;
import com.bottle.model.dto.request.FindPersonRequest;
import com.bottle.model.dto.response.FindPersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindPersonService {

    private final UserRepository userRepository;

    @Autowired
    public FindPersonService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findFromDB(String request) {
        List<User> userList = userRepository.findAll();
        List<String> requestList;

        if (request != null && !request.isEmpty()) {
//            userList = userRepository.getAllUsersLike("%" + request.getSearch().toLowerCase() + "%");
//            userList = userRepository.getAllUsersIn(Arrays.asList(request.toLowerCase().split(" ")));
            requestList = Arrays.asList(request.toLowerCase().split(" "));
            userList = userList.stream().filter(user -> requestList.stream()
                    .anyMatch(req -> user.getName().toLowerCase().contains(req)
                            || user.getSurname().toLowerCase().contains(req)))
                    .collect(Collectors.toList());
        }

// TODO: 24.04.2018 write build method
//        for (User user : userList) {
//            UserDTO dto = new UserDTO();
//            dto.setId(user.getId());
//            dto.setAge(user.getAge());
//            dto.setAvatarUrl(user.getAvatarUrl());
//            dto.setCity(user.getCity());
//            dto.setCountry(user.getCountry());
//            dto.setName(user.getName());
//            dto.setSurname(user.getSurname());
////            dtoList.add(dto);
//        }
        return userList;
    }
}
