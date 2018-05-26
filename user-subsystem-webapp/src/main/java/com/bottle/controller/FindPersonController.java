package com.bottle.controller;

import com.bottle.service.FindPersonService;
import com.bottle.repository.UserRepository;
import com.bottle.model.dto.request.FindPersonRequest;
import com.bottle.model.dto.response.FindPersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
        System.out.println("request contents: " + request.getSearch() + " " + request.getSearchType());
        // TODO: 24.04.2018 why? need normal service
        return findPersonService.findFromDB(request);
    }

}
