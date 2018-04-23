package com.bottle.controller;
import com.bottle.entity.User;
import com.bottle.model.dto.UserDTO;
import com.bottle.repository.UserRepository;
import com.bottle.model.dto.request.InfoPageRequest;
import com.bottle.model.dto.responce.InfoPageResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller

public class InfoPageController {
    private UserRepository userRepository;

    @Autowired
    public InfoPageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/showUsers")
    @ResponseBody
    public InfoPageResponce getUserInfo(InfoPageRequest request) {

        User user = userRepository.findOne(request.getId());
//        User user = userRepository.findOne(UUID.fromString("14dd28b2-1e7e-4575-9923-135c4fbf345b"));
        UserDTO userDTO = new UserDTO();

        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setAge(user.getAge());

        System.out.println("TestController: A name of the userDTO is " + userDTO.getName());
        return new InfoPageResponce(userDTO);
    }
}
