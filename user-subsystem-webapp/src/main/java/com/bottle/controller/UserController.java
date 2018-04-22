package com.bottle.controller;

import com.bottle.entity.User;
import com.bottle.model.dto.UserDTO;
import com.bottle.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
@NoArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/get_by_id", method = RequestMethod.POST)
    public UserDTO addOneWayRelationship(@RequestParam(name = "userId") UUID userId) {
        User user = userRepository.getUserById(userId);
        return getUserDTO(user);
    }

    @PostMapping("/get_all")
    @ResponseBody
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.getAllByDeletedFalse();
        return getListUserDTO(users);
    }

    private List<UserDTO> getListUserDTO(List<User> users) {
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(getUserDTO(user));
        }
        return usersDTO;
    }

    private UserDTO getUserDTO(User user) {
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

