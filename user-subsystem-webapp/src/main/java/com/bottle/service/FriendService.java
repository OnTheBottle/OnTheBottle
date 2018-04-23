package com.bottle.service;

import com.bottle.entity.User;
import com.bottle.model.dto.UserDTO;
import com.bottle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

    // TODO: 24.04.2018 why?????
    public boolean addTwoWayRelation(UUID firstId, UUID secondId) {
        addOneWayRelation(firstId, secondId);
        addOneWayRelation(secondId, firstId);
        return true;
    }
    // TODO: 24.04.2018 why?????
    public boolean addOneWayRelation(UUID firstId, UUID secondId) {
        User firstUser = userRepository.getUserById(firstId);
        User secondUser = userRepository.getUserById(secondId);
        firstUser.getFriends().add(secondUser);
        userRepository.save(firstUser);
        return true;
    }
    // TODO: 24.04.2018 better use db
    public Set<UserDTO> getConfirmedFriends(UUID id) {
        User user = userRepository.getUserById(id);
        Set<User> friends = user.getFriends();
        Set<User> confirmedFriends = new HashSet<>();

        for (User friend : friends) {
            if (friend.getFriends().contains(user)) {
                confirmedFriends.add(friend);
            }
        }
        return userService.getUsersDTO(confirmedFriends);
    }
}


