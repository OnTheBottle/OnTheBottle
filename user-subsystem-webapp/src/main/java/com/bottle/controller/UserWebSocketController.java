package com.bottle.controller;

import com.bottle.client.MessageSubsystemClient;
import com.bottle.service.AuthService;
import com.bottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Map;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserWebSocketController {

    private UserService userService;
    private AuthService authService;

    @Autowired
    public UserWebSocketController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic")
    public boolean connectUser(@Payload String token, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("token:" + token);
        if (authService.isValidToken(token)) {
            UUID authId = authService.getAuthId(token);
            userService.setOnlineStatus(authId);
            headerAccessor.getSessionAttributes().put("userId", authId);
            return true;
        }
        return false;


    }
}
