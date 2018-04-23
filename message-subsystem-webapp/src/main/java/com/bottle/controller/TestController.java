package com.bottle.controller;

import com.bottle.model.DTO.response.ListResponseDTO;
import com.bottle.model.entity.User;
import com.bottle.service.place.AllPlaceService;
import com.bottle.service.user.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/test")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestController {
    private AllPlaceService allPlaceService;
    private AllUserService allUserService;

    @Autowired
    public TestController(AllPlaceService allPlaceService, AllUserService allUserService) {
        this.allPlaceService = allPlaceService;
        this.allUserService = allUserService;
    }

//    @PostMapping(path = "/createUser")
 //   public void createUser() {
  //      allUserService.createUser(User user);
  //  }

    @PostMapping(path = "/createPlace")
    public void createPlace() {
        allPlaceService.createPlace();
    }

    @PostMapping(path = "/getUsers")
    public ListResponseDTO<User> getUsers() {
        ListResponseDTO<User> users = new ListResponseDTO<>();
        users.setList(allUserService.getAllUsers());
        return users;
    }
}
