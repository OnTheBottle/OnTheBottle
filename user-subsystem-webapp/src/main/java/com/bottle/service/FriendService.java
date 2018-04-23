package com.bottle.service;

import com.bottle.entity.User;
import com.bottle.model.dto.UserDTO;
import com.bottle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class FriendService {

    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public FriendService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public boolean addTwoWayRelation(UUID firstId, UUID secondId){
        addOneWayRelation(firstId, secondId);
        addOneWayRelation(secondId, firstId);
        return true;
    }

    public boolean addOneWayRelation(UUID firstId, UUID secondId){
        User firstUser = userRepository.getUserById(firstId);
        User secondUser = userRepository.getUserById(secondId);
        firstUser.getFriends().add(secondUser);
        userRepository.save(firstUser);
        return true;
    }

    public Set<UserDTO> getFriendsByUserId(UUID id){
        User user = userRepository.getUserById(id);
        return userService.getUsersDTO(user.getFriends());
    }
}


