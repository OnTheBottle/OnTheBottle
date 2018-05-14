package com.bottle.controller;


import com.bottle.model.dto.UserDTO;
import com.bottle.model.dto.request.UsersIdDTO;
import com.bottle.service.AuthService;
import com.bottle.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
@NoArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private UserService userService;
    private AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
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

    @RequestMapping(path = "/getUsersInfo", method = RequestMethod.POST)  //TODO
    public ResponseEntity<?> leaveUserEvent(@RequestBody UsersIdDTO usersIdDTO,
                                            @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return getNonValidTokenResponse();

        usersIdDTO.getUsers().forEach(System.out::println);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<String> getNonValidTokenResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "text/plain");
        String message = "Non-valid token";
        return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
    }
}
