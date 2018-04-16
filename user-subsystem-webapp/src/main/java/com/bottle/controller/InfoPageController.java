package com.bottle.controller;
import com.bottle.entity.User;
import com.bottle.repository.UserRepository;
import com.bottle.model.dto.request.InfoPageRequest;
import com.bottle.model.dto.responce.InfoPageResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        System.out.println("TestController: A name of the user is " + user.getName());
        return new InfoPageResponce(user);
    }
}
