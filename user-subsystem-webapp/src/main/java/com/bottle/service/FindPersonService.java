package com.bottle.service;

import com.bottle.entity.User;
import com.bottle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<User> findPersons(String request) {
        List<User> userList = userRepository.findAll();
        List<String> requestList;

        if (request != null && !request.isEmpty()) {
            requestList = Arrays.asList(request.toLowerCase().split(" "));
            userList = userList.stream().filter(user -> requestList.stream()
                    .anyMatch(req -> user.getName().toLowerCase().contains(req)
                            || user.getSurname().toLowerCase().contains(req)))
                    .collect(Collectors.toList());
        }
        return userList;
    }
}
