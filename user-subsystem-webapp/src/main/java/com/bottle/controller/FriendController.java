package com.bottle.controller;

import com.bottle.entity.User;
import com.bottle.model.dto.UserDTO;
import com.bottle.repository.UserRepository;
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

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @RequestMapping(path = "/add_oneway_relation", method = RequestMethod.POST)
    public boolean addOneWayRelationship(
            @RequestParam(name = "firstFriendId") UUID firstFriendId,
            @RequestParam(name = "secondFriendId") UUID secondFriendId) {
        return friendService.addOneWayRelation(firstFriendId, secondFriendId);
    }

    @RequestMapping(path = "/add_twoway_relation", method = RequestMethod.POST)
    public boolean addTwoWayRelationship(
            @RequestParam(name = "firstFriendId") UUID firstFriendId,
            @RequestParam(name = "secondFriendId") UUID secondFriendId) {
        return friendService.addTwoWayRelation(firstFriendId, secondFriendId);
    }

    @RequestMapping(path = "/get_friends_by_userid", method = RequestMethod.POST)
    public Set<UserDTO> getFriendsByUserId(
            @RequestParam(value = "id") UUID userId) {
        return friendService.getFriendsByUserId(userId);
    }

}
