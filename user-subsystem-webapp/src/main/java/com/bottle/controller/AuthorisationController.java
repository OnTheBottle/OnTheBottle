package com.bottle.controller;

import com.bottle.entity.User;
import com.bottle.model.dto.request.LoginRequest;
import com.bottle.model.dto.request.RegistrationRequest;
import com.bottle.model.dto.responce.LoginResponse;
import com.bottle.model.dto.responce.RegistrationResponse;
import com.bottle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthorisationController {
    private UserRepository userRepository;

    @Autowired
    public AuthorisationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/authorize")
    @ResponseBody
    public LoginResponse login(LoginRequest request) {
        System.out.println("request is "+request.getLogin()+ request.getPassword());
        LoginResponse response = new LoginResponse();
        User userByLogin = userRepository.findByLogin(request.getLogin());
        System.out.println("login controller: "+ userByLogin.getName());
        if (userByLogin != null) {
            if (userByLogin.getPassword() == request.getPassword()) {
                response.setUuid(userByLogin.getId());
            }
        }
        return response;
    }

    @PostMapping("/registration")
    @ResponseBody
    public RegistrationResponse registration(RegistrationRequest request) {
        System.out.println(request);
        System.out.println("email: " + request.getEmail());
        System.out.println("login: " + request.getLogin());
        RegistrationResponse response = new RegistrationResponse();
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
        return response;
    }
}
