package com.bottle.findPerson.controller;

import com.bottle.findPerson.logic.FindPerson;
import com.bottle.findPerson.repository.UserRepository;
import com.bottle.findPerson.request.Request;
import com.bottle.findPerson.response.Response;
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
    public Response getListOfPersons(Request request) {

        return new FindPerson().findFromDB(userRepository, request);
    }


}
