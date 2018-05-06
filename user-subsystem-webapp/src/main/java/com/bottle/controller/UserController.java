package com.bottle.controller;


import com.bottle.model.dto.UserDTO;
import com.bottle.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
@NoArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/get_by_id", method = RequestMethod.POST)
    public UserDTO getUserById(@RequestParam(name = "userId") UUID userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/get_all")
    @ResponseBody
    public Set<UserDTO> getUsers() {
        return userService.getUsers();
    }

}
