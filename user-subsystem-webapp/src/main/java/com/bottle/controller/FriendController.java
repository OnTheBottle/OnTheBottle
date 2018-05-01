package com.bottle.controller;

import com.bottle.model.dto.UserDTO;
import com.bottle.service.FriendService;
import com.bottle.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/friend")
@NoArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FriendController {

    private FriendService friendService;
    private UserService userService;

    @Autowired
    public FriendController(FriendService friendService, UserService userService) {
        this.friendService = friendService;
        this.userService = userService;
    }

    @RequestMapping(path = "/add_oneway_relation", method = RequestMethod.POST)
    public void addOneWayRelationship(
            @RequestParam(name = "firstFriendId") UUID firstId,
            @RequestParam(name = "secondFriendId") UUID secondId) {
        if (userService.isUserById(firstId) && userService.isUserById(secondId)) {
            friendService.addOneWayRelation(firstId, secondId);
        }
    }

    @RequestMapping(path = "/add_twoway_relation", method = RequestMethod.POST)
    public void addTwoWayRelationship(
            @RequestParam(name = "firstFriendId") UUID firstId,
            @RequestParam(name = "secondFriendId") UUID secondId) {
        if (userService.isUserById(firstId) && userService.isUserById(secondId)) {
            friendService.addTwoWayRelation(firstId, secondId);
        }
    }

    @RequestMapping(path = "/get_friends_by_userid", method = RequestMethod.POST)
    public Set<UserDTO> getFriendsByUserId(
            @RequestParam(value = "id") UUID userId) {
        System.out.println("req get_friends_by_userid:" + userId);
        if (userService.isUserById(userId)) {
            return friendService.getConfirmedFriends(userId);
        }
        return new HashSet<>();
    }

}
