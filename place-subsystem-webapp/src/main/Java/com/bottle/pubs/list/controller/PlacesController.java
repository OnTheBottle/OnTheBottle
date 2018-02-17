package com.bottle.pubs.list.controller;

import com.bottle.pubs.list.logic.FindPlace;
import com.bottle.pubs.list.repository.PubsRepository;
import com.bottle.pubs.list.request.Request;
import com.bottle.pubs.list.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PlacesController {

    private PubsRepository pubsRepository;

    @Autowired
    public PlacesController(PubsRepository pubsRepository){
        this.pubsRepository = pubsRepository;
    }

    @GetMapping(path = "/getPubs")
    @ResponseBody
    public Response getPubs(Request request) {
        return new FindPlace().doSearch(pubsRepository, request);
    }
}
