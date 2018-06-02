package com.bottle.controller;

import com.bottle.entity.User;
import com.bottle.model.dto.UserDTO;
import com.bottle.service.FindPersonService;
import com.bottle.repository.UserRepository;
import com.bottle.model.dto.request.FindPersonRequest;
import com.bottle.model.dto.response.FindPersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FindPersonController {

    private final FindPersonService findPersonService;

    @Autowired
    public FindPersonController(FindPersonService findPersonService) {
        this.findPersonService = findPersonService;
    }

    @PostMapping("/person_search")
    @ResponseBody
    public FindPersonResponse getListOfPersons(FindPersonRequest request) {
        System.out.println("request contents: " + request.getSearch());
        FindPersonResponse response = new FindPersonResponse();
        List<User> userList = findPersonService.findFromDB(request.getSearch());
        List<UserDTO> dtoList = new ArrayList<>();
        for (User user : userList) {
            UserDTO userDTO = UserDTO.newBuilder()
                    .setId(user.getId())
                    .setName(user.getName())
                    .setSurname(user.getSurname())
                    .setAge(user.getAge())
                    .setAvatarUrl(user.getAvatarUrl())
                    .setCity(user.getCity())
                    .setCountry(user.getCountry())
                    .build();
            dtoList.add(userDTO);
        }
        response.setListOfPersons(dtoList);

        return response;
    }
}
