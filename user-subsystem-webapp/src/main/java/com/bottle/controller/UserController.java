package com.bottle.controller;


import com.bottle.entity.User;
import com.bottle.model.dto.SmallUserDTO;
import com.bottle.model.dto.UserDTO;
import com.bottle.model.dto.request.UserIdDTO;
import com.bottle.service.AuthService;
import com.bottle.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @RequestMapping(path = "/getUser", method = RequestMethod.POST)
    public UserDTO getUser(
            @RequestParam(name = "access_token") String token,
            @RequestParam(value = "userId") UUID userId) {
        if (authService.isValidToken( token )) {
            if (userService.isUserById( userId )) {
                return userService.getUserDTO( userService.getUser( userId ) );
            }
        }
        return null;
    }

    @RequestMapping(path = "/getUsers", method = RequestMethod.POST)
    public Set<UserDTO> getUsers(
            @RequestParam(name = "access_token") String token,
            @RequestParam(value = "usersId") Set<UUID> uuidSet) {
        System.out.println( "getUsers uuidSet: " + uuidSet );
        if (authService.isValidToken( token )) {
            Set<UserDTO> userDTOSet = userService.getUsersDTO( userService.getUsers( uuidSet ) );
            System.out.println( "userDTOSet:" + userDTOSet );
            return userDTOSet;
            //return userService.getUsersDTO(userService.getUsers(uuidSet));
        }
        return null;
    }

    @Deprecated
    @RequestMapping(path = "/get_by_id", method = RequestMethod.POST)
    public UserDTO getUserById(@RequestParam(name = "userId") UUID userId) {
        return userService.getUserById( userId );
    }

    @PostMapping("/get_all")
    @ResponseBody
    public Set<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(path = "/getSmallInfo", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestParam(name = "userId") UUID userId,
                                     @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) return ErrorResponse.getErrorResponse( "Non-valid token" );
        return new ResponseEntity<>( userService.getSmallInfoUser( userId ), HttpStatus.OK );
    }

    @RequestMapping(path = "/getUsersInfo", method = RequestMethod.POST)
    public ResponseEntity<?> getUsersInfo(@RequestBody List<UserIdDTO> usersId,
                                          @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) return ErrorResponse.getErrorResponse( "Non-valid token" );
        Set<UserDTO> users = userService.getUsersInfo( usersId );
        return new ResponseEntity<>( users, HttpStatus.OK );
    }

    @RequestMapping(path = "/addAvatarUrl", method = RequestMethod.POST)
    public ResponseEntity<?> addAvatar(@RequestParam(name = "avatarUrl") String url,
                                     @RequestParam(name = "userId") UUID userId,
                                     @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken( token )) return ErrorResponse.getErrorResponse( "Non-valid token" );
        User user = userService.getUser( userId );
        user.setAvatarUrl( url );
        userService.updateUser( user );
        return new ResponseEntity<>( HttpStatus.OK );
    }
}
