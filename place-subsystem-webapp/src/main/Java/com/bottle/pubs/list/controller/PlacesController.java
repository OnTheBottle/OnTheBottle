package com.bottle.pubs.list.controller;

import com.bottle.pubs.list.logic.FindPlace;
import com.bottle.pubs.list.repository.PubsRepository;
import com.bottle.pubs.list.request.PubSearchQueryDTO;
import com.bottle.pubs.list.response.PlacesListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlacesController {

    private PubsRepository pubsRepository;

    @Autowired
    public PlacesController(PubsRepository pubsRepository) {
        this.pubsRepository = pubsRepository;
    }

    @PostMapping(path = "/getPubs")
    public PlacesListDTO getPubs(PubSearchQueryDTO request) {
        return new FindPlace().doSearch(pubsRepository, request);
    }

}
