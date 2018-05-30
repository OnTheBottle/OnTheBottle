package com.bottle.controller;

import com.bottle.model.dto.UserDTO;
import com.bottle.service.AuthService;
import com.bottle.service.FriendService;
import com.bottle.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/remove_relationship", method = RequestMethod.POST)
    public void removeRelationship(
            @RequestParam(name = "access_token") String token,
            @RequestParam(name = "userId") UUID friendId) {
        if (authService.isValidToken(token)) {
            UUID authId = authService.getAuthId(token);
            friendService.removeRelationship(authId, friendId);
        }
    }

    @RequestMapping(path = "/get_confirmed_friends", method = RequestMethod.POST)
    public Set<UserDTO> getConfirmedFriendsDTO(
            @RequestParam(name = "access_token") String token,
            @RequestParam(value = "userId") UUID userId) {
        String select = "confirmed";
        return getFriendsDTO(token, userId, select);
    }

    @RequestMapping(path = "/get_status", method = RequestMethod.POST)
    public UserDTO getFriendStatus(
            @RequestParam(name = "access_token") String token,
            @RequestParam(value = "userId") UUID userId) {
        if (authService.isValidToken(token)) {
            if (userService.isUserById(userId)) {
                UUID authId = authService.getAuthId(token);
                return userService.getUserDTO(friendService.getFriendStatus(userId, authId));
            }
        }
        return null;
    }

    @RequestMapping(path = "/is_confirmed_friend", method = RequestMethod.POST)
    public boolean isConfirmedFriend(
            @RequestParam(name = "access_token") String token,
            @RequestParam(value = "userId") UUID userId) {
        if (authService.isValidToken(token)) {
            if (userService.isUserById(userId)) {
                UUID authId = authService.getAuthId(token);
                return friendService.isFriend(authId, userId);
            }
        }
        return false;
    }

    @RequestMapping(path = "/get_notconfirmed_friends", method = RequestMethod.POST)
    public Set<UserDTO> getNotConfirmedFriends(
            @RequestParam(name = "access_token") String token,
            @RequestParam(value = "userId") UUID userId) {
        String select = "notconfirmed";
        return getFriendsDTO(token, userId, select);
    }

    @RequestMapping(path = "/get_requested_friends", method = RequestMethod.POST)
    public Set<UserDTO> getRequestedFriendsDTO(
            @RequestParam(name = "access_token") String token,
            @RequestParam(value = "userId") UUID userId) {
        String select = "requested";
        return getFriendsDTO(token, userId, select);
    }

    private Set<UserDTO> getFriendsDTO(String token, UUID userId, String select) {
        if (authService.isValidToken(token)) {
            if (userService.isUserById(userId)) {
                UUID authId = authService.getAuthId(token);
                return userService.getUsersDTO(friendService.getFriends(authId, userId, select));
            }
        }
        return null;
    }

/*
    private UserDTO getFriendStatusDTO(String token, UUID userId) {
        if (authService.isValidToken(token)) {
            if (userService.isUserById(userId)) {
                UUID authId = authService.getAuthId(token);
                return userService.getUserDTO(friendService.getFriendStatus(userId, authId));
            }
        }
        return null;
    }
*/
}


