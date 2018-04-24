package com.bottle.service;

import com.bottle.entity.User;
import com.bottle.model.dto.UserDTO;
import com.bottle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDTO getUserById(UUID id) {
        User user = userRepository.getUserById(id);
        return getUserDTO(user);
    }

    public Set<UserDTO> getUsers() {
        Set<User> users = userRepository.getAllByDeletedFalse();
        return getUsersDTO(users);
    }

    public Set<UserDTO> getUsersDTO(Set<User> users) {
        Set<UserDTO> usersDTO = new HashSet<>();
        for (User user : users) {
            usersDTO.add(getUserDTO(user));
        }
        return usersDTO;
    }

    public UserDTO getUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setId(user.getId());
        userDTO.setAge(user.getAge());
        userDTO.setAvatarUrl(user.getAvatarUrl());
        userDTO.setCity(user.getCity());
        userDTO.setCountry(user.getCountry());
        List<UUID> friends = new ArrayList<>();

        for (User friend : user.getFriends()) {
            friends.add(friend.getId());
        }
        userDTO.setFriendsId(friends);
        return userDTO;
    }

}