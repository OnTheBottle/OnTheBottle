package com.bottle.controller;

import com.bottle.model.entity.User;
import com.bottle.model.repository.UserRepository;
import com.bottle.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SyncController {

    private final UserRepository userRepository;
    private final AuthService authService;


    @Autowired
    public SyncController(AuthService authService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @RequestMapping(path = "/user/add_user", method = RequestMethod.POST)
    public void addUser(
            @RequestParam(name = "access_token") String token) {
        if (authService.isValidToken(token)) {
            UUID id = authService.getAuthId(token);
            System.out.println("id: " + id);
            if (!userRepository.exists(id)) {
                User user = new User();
                user.setId(id);
                userRepository.save(user);
            }
        }
    }
}

