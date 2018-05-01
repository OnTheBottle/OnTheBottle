package com.bottle.controller;

import com.bottle.model.dto.request.AuthDTO;
import com.bottle.model.dto.request.RegistrationDTO;
import com.bottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/authorization")
    public boolean authUser(AuthDTO authDTO) {
        return userService.authUser(authDTO);
    }

    @PostMapping(path = "/registration")
    public boolean addNewUser(RegistrationDTO userDTO) {
        System.out.println("userDTO: " + userDTO);
        System.out.println("getLogin: " + userDTO.getLogin());
        return userService.addNewUser(userDTO);
    }

/*
    @PostMapping("/authorigation")
    @ResponseBody
    public LoginResponse userLogin(AuthDTO request) {
        System.out.println("AuthDTO is " + request.getLogin() + request.getPassword());
        LoginResponse response = new LoginResponse();
        User userByLogin = userRepository.findByLogin(request.getLogin());
        // TODO: 24.04.2018 need add normal error message
        if (userByLogin != null) {
            if (userByLogin.getPassword().equals(request.getPassword())) {
//                response.setUuid(userByLogin.getId().toString());
                response.setMessage("complete");
            } else {
                response.setMessage("not exist");
            }
        }
        return response;
    }

    @PostMapping("/registration")
    @ResponseBody
    public RegistrationResponse registration(RegistrationDTO request) {
        RegistrationResponse response = new RegistrationResponse();
        // TODO: 24.04.2018 need use uniq in db
        User userByEmail = userRepository.findByEmail(request.getEmail());
        User userByLogin = userRepository.findByLogin(request.getLogin());
        if (userByEmail == null && userByLogin == null) {
            User newUser = new User(request);
            userRepository.save(newUser);
            response.setSuccessful(true);
        } else {
            response.setSuccessful(false);
            System.out.println("The user already exists!");
        }
        // TODO: 24.04.2018 need normal response
        return response;
    }
*/
}

