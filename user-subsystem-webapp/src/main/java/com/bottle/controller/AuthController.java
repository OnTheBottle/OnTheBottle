package com.bottle.controller;

import com.bottle.model.dto.request.ReqRegDTO;
import com.bottle.model.dto.request.ReqAuthDTO;
import com.bottle.model.dto.response.RespAuthDTO;
import com.bottle.service.AuthService;
import com.bottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(path = "/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private UserService userService;
    private AuthService authService;

    @Autowired
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping(path = "/verify")
    public boolean verifyToken(@RequestParam(name = "access_token") String token){
        return authService.isValidToken(token);
    }

    @PostMapping(path = "/authorization")
    public RespAuthDTO authUser(ReqAuthDTO userDTO) {
        RespAuthDTO respAuthDTO = new RespAuthDTO();
        if (userService.isAuth(userDTO)) {
            String token = authService.getTokenByLogin(userDTO.getLogin());
            respAuthDTO.setToken(token);
        }
        return respAuthDTO;
    }

    @PostMapping(path = "/authorization-test")
    public RespAuthDTO authUserTest(ReqAuthDTO userDTO, HttpServletResponse response) {
        RespAuthDTO respAuthDTO = new RespAuthDTO();
        if (userService.isAuth(userDTO)) {
            String token = authService.getTokenByLogin(userDTO.getLogin());
            respAuthDTO.setToken(token);
            response.addCookie(new Cookie("access_token",token));
        }
        return respAuthDTO;
    }

    @PostMapping(path = "/registration")
    public RespAuthDTO addNewUser(ReqRegDTO userDTO, HttpServletResponse response) {
        RespAuthDTO respAuthDTO = new RespAuthDTO();
        if (userService.addNewUser(userDTO)) {
            String token = authService.getTokenByLogin(userDTO.getLogin());
            respAuthDTO.setToken(token);
            response.addCookie(new Cookie("access_token", token ));
        }
        return respAuthDTO;
    }

}

