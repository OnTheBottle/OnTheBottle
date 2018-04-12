package com.bottle.pubs.list.logic;

import com.bottle.pubs.list.entity.Place;
import com.bottle.pubs.list.repository.PubsRepository;
import com.bottle.pubs.list.request.PubSearchQueryDTO;
import com.bottle.pubs.list.response.PlacesListDTO;

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
