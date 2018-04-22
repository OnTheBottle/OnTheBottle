package com.bottle.pubs.places.logic;

import com.bottle.pubs.places.entity.Place;
import com.bottle.pubs.places.repository.PubsRepository;
import com.bottle.pubs.places.request.PubSearchQueryDTO;
import com.bottle.pubs.places.response.PlacesListDTO;

import java.util.ArrayList;
import java.util.List;

public class FindPlace {
    public PlacesListDTO doSearch(PubsRepository pubsRepository, PubSearchQueryDTO request) {
        PlacesListDTO response = new PlacesListDTO();
        if (request.isShowAll()) {
            List<Place> allPlaces = new ArrayList<>();
            pubsRepository.findAll().forEach(allPlaces::add);
            response.setPlaces(allPlaces);
        }
        return response;
    }
}
