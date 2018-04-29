package com.bottle.controller;

import com.bottle.model.dto.request.ReqRegDTO;
import com.bottle.model.dto.request.ReqAuthDTO;
import com.bottle.model.dto.response.RespAuthDTO;
import com.bottle.service.AuthService;
import com.bottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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

    @GetMapping("/forwardWithForwardPrefix")
    public ModelAndView redirectWithUsingForwardPrefix(ModelMap model) {
        System.out.println(model);
        model.addAttribute("attribute", "forwardWithForwardPrefix");
        return new ModelAndView("forward:/google.com.ua", model);
    }

    @PostMapping(path = "/authorigation")
    public RespAuthDTO authUser(ReqAuthDTO userDTO) {
        RespAuthDTO respAuthDTO = new RespAuthDTO();
        if (userService.isAuth(userDTO)) {
            respAuthDTO = authService.getTokenByLogin(userDTO.getLogin());
        }
        return respAuthDTO;
    }

    @PostMapping(path = "/registration")
    public RespAuthDTO addNewUser(ReqRegDTO userDTO) {
        RespAuthDTO respAuthDTO = new RespAuthDTO();
        if (userService.addNewUser(userDTO)) {
            respAuthDTO = authService.getTokenByLogin(userDTO.getLogin());
        }
        return respAuthDTO;
    }

}

