package com.bottle.controller;

import com.bottle.logic.FindPerson;
import com.bottle.repository.UserRepository;
import com.bottle.model.dto.request.FindPersonRequest;
import com.bottle.model.dto.responce.FindPersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FindPersonController {

    private final UserRepository userRepository;
@Autowired
    public FindPersonController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/person_search")
    @ResponseBody
    public FindPersonResponse getListOfPersons(FindPersonRequest request) {

        return new FindPerson().findFromDB(userRepository, request);
    }


}
