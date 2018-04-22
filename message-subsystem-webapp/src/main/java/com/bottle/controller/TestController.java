package com.bottle.controller;

import com.bottle.model.DTO.EventDTO;
import com.bottle.model.DTO.request.IdEventAndUserRequestDTO;
import com.bottle.model.DTO.request.IdRequestDTO;
import com.bottle.model.DTO.response.EventsResponseDTO;
import com.bottle.model.DTO.response.ListResponseDTO;
import com.bottle.model.DTO.response.ResultResponseDTO;
import com.bottle.model.DTO.validators.EventValidator;
import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import com.bottle.service.event.AllEventService;
import com.bottle.service.place.AllPlaceService;
import com.bottle.service.user.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @PostMapping(path = "/addFriend")
    public void addFriend() {
        UUID userIdOne = UUID.fromString("c4f63463-beb9-42c7-8921-7d3184845689"); //TODO брать значения с фронта
        UUID userIdTwo = UUID.fromString("36fe8f70-3287-4521-b00f-807682ab8204");
        allUserService.addFriend(userIdOne, userIdTwo);
    }
}
