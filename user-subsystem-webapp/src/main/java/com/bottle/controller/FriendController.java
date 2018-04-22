package com.bottle.controller;

import com.bottle.entity.User;
import com.bottle.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/friend")
@NoArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FriendController {

    private UserRepository userRepository;

    @Autowired
    public FriendController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/add_oneway_relation", method = RequestMethod.POST)
    public boolean addOneWayRelationship(
            @RequestParam(name = "firstFriendId") UUID firstFriendId,
            @RequestParam(name = "secondFriendId") UUID secondFriendId
    ) {
        User firstUser;
        User secondUser;

        firstUser = userRepository.getUserById(firstFriendId);
        secondUser = userRepository.getUserById(secondFriendId);
        firstUser.getFriends().add(secondUser);
        userRepository.save(firstUser);
        System.out.println("1 user:" + firstUser.getFriends());
        System.out.println("2 user:" + secondUser.getFriends());
        return true;
    }

    @RequestMapping(path = "/add_twoway_relation", method = RequestMethod.POST)
    public boolean addTwoWayRelationship(
            @RequestParam(name = "firstFriendId") UUID firstFriendId,
            @RequestParam(name = "secondFriendId") UUID secondFriendId
    ) {
        User firstUser;
        User secondUser;

        firstUser = userRepository.getUserById(firstFriendId);
        secondUser = userRepository.getUserById(secondFriendId);
        firstUser.getFriends().add(secondUser);
        secondUser.getFriends().add(firstUser);
        userRepository.save(firstUser);
        userRepository.save(secondUser);

        return true;
    }
}
