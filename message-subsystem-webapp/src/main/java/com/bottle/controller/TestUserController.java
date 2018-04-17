package com.bottle.controller;

import com.bottle.Properties;
import com.bottle.model.DTO.UserDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestUserController {

    @RequestMapping(value = "/getfriends")
    public List<UserDTO> getFriends(@RequestParam(value = "id") UUID id) {
        System.out.println("UUID from news to user subsystem: " + id);
        String avatarPath = Properties.SUBMESSAGEPATH + "/images/";
        List<UserDTO> friends = new ArrayList<>();
        friends.add(new UserDTO(UUID.fromString("c4f63463-beb9-42c7-8921-7d3184845689"), "Vasja", "Test", avatarPath + "11.jpg"));
        friends.add(new UserDTO(UUID.fromString("36fe8f70-3287-4521-b00f-807682ab8204"), "Dima", "Test", avatarPath + "16.jpg"));
        friends.add(new UserDTO(UUID.fromString("bfae92d5-84c1-46ec-afe4-57a44bfac85e"), "Olga", "Test", avatarPath + "1397.jpg"));
        friends.add(new UserDTO(UUID.fromString("9fe021e2-f725-457c-9056-af0945a43686"), "Petya", "Test", avatarPath + "lion.jpg"));
        friends.add(new UserDTO(UUID.fromString("bd04fb75-c3ab-46f0-a204-6cd221a39c86"), "Katya", "Test", avatarPath + "68572.jpeg"));
        friends.add(new UserDTO(UUID.fromString("d97aeaa5-afa6-4d0a-bb53-eec0ed54e7f9"), "Sasha", "Test", avatarPath + "914.gif"));
        return friends;
    }
}
