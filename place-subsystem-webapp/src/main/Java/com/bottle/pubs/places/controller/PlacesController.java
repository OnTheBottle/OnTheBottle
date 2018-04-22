package com.bottle.pubs.places.controller;


import com.bottle.pubs.places.logic.FindPlace;
import com.bottle.pubs.places.repository.PubsRepository;
import com.bottle.pubs.places.request.PubSearchQueryDTO;
import com.bottle.pubs.places.response.PlacesListDTO;
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

    @PostMapping(path = "/pubList")
    public PlacesListDTO getPubs(PubSearchQueryDTO request) {
        return new FindPlace().doSearch(pubsRepository, request);
    }

}
