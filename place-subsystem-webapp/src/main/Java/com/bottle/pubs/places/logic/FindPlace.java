package com.bottle.pubs.places.logic;

import com.bottle.pubs.places.entity.Place;
import com.bottle.pubs.places.repository.PubsRepository;
import com.bottle.pubs.places.request.PubSearchQueryDTO;
import com.bottle.pubs.places.response.PlacesListDTO;

import java.util.ArrayList;
import java.util.List;

public class FindPlace {
    // TODO: 24.04.2018 what the fuck?
    public PlacesListDTO doSearch(PubsRepository pubsRepository, PubSearchQueryDTO request) {
        PlacesListDTO response = new PlacesListDTO();
        response.setPlaces(pubsRepository.findAll());
        return response;
    }
}
