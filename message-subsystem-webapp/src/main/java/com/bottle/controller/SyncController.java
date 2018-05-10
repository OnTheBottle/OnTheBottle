package com.bottle.controller;

import com.bottle.service.auth.AuthService;
import com.bottle.service.news.NewsService;
import com.bottle.service.user.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SyncController {

    private final AllUserService userService;
    private final AuthService authService;


    @Autowired
    public SyncController(AuthService authService, AllUserService userService) {
        this.userService = userService;
        this.authService = authService;
    }

    @RequestMapping(path = "/user/add_user", method = RequestMethod.POST)
    public void addUser(
            @RequestParam(name = "access_token") String token) {
        System.out.println("addUser /user/add_user token:\n" + token);
        if (authService.isValidToken(token)) {
            UUID id = authService.getAuthId(token);
            System.out.println("id: " + id);
            if (!userService.isExistUserById(id)){
                System.out.println("it is new user id: " + id);
                userService.addUserById(id);
            }
        }
    }
}

