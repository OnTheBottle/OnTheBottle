package com.bottle.controller;

import com.bottle.model.dto.UserDTO;
import com.bottle.service.AuthService;
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
    private AuthService authService;


    @Autowired
    public FriendController(FriendService friendService, UserService userService, AuthService authService) {
        this.friendService = friendService;
        this.userService = userService;
        this.authService = authService;
    }

    @RequestMapping(path = "/add_oneway_relation", method = RequestMethod.POST)
    public void addOneWayRelationship(
            @RequestParam(name = "access_token") String token,
            @RequestParam(name = "userId") UUID friendId) {
        if (authService.isValidToken(token)) {
            if (userService.isUserById(friendId)) {
                UUID authId = authService.getAuthId(token);
                friendService.addOneWayRelation(authId, friendId);
            }
        }
    }

    @RequestMapping(path = "/add_twoway_relation", method = RequestMethod.POST)
    public void addTwoWayRelationship(
            @RequestParam(name = "access_token") String token,
            @RequestParam(name = "userId") UUID friendId) {
        if (authService.isValidToken(token)) {
            if (userService.isUserById(friendId)) {
                UUID authId = authService.getAuthId(token);
                friendService.addTwoWayRelation(authId, friendId);
            }
        }
    }


    @RequestMapping(path = "/manual_add_oneway_relation", method = RequestMethod.POST)
    public void addOneWayRelationshipManual(
            @RequestParam(name = "firstFriendId") UUID firstId, // Whose want relationship
            @RequestParam(name = "secondFriendId") UUID secondId) {
        if (userService.isUserById(firstId) && userService.isUserById(secondId)) {
            friendService.addOneWayRelation(firstId, secondId);
        }
    }


    @RequestMapping(path = "/manual_add_twoway_relation", method = RequestMethod.POST)
    public void addTwoWayRelationshipManual(
            @RequestParam(name = "firstFriendId") UUID firstId,
            @RequestParam(name = "secondFriendId") UUID secondId) {
        if (userService.isUserById(firstId) && userService.isUserById(secondId)) {
            friendService.addTwoWayRelation(firstId, secondId);
        }
    }

    @RequestMapping(path = "/get_users_want_relationship", method = RequestMethod.POST)
    public Set<UserDTO> getUsersWantRelationship(
            @RequestParam(name = "access_token") String token) {
        if (authService.isValidToken(token)) {
            UUID authId = authService.getAuthId(token);
            return friendService.getNotConfirmedFriends(authId);
        }
        return null;
    }

    @RequestMapping(path = "/remove_relationship", method = RequestMethod.POST)
    public void removeRelationship(
            @RequestParam(name = "access_token") String token,
            @RequestParam(name = "userId") UUID friendId) {
        if (authService.isValidToken(token)) {
            UUID authId = authService.getAuthId(token);
            friendService.removeRelationship(authId, friendId);
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
