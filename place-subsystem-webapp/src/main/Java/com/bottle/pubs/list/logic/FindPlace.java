package com.bottle.pubs.list.logic;

import com.bottle.pubs.list.repository.PubsRepository;
import com.bottle.pubs.list.request.PubSearchQueryDTO;
import com.bottle.pubs.list.response.PlacesListDTO;

public class FindPlace {
    public PlacesListDTO doSearch(PubsRepository pubsRepository, PubSearchQueryDTO request) {
        PlacesListDTO response = new PlacesListDTO();
        if (request.isShowAll()) {
            response.setPlaces(pubsRepository.getAll());
        } else {
            switch (request.getSearchType().toLowerCase()) {
                case "name":
                    response.setPlaces(pubsRepository.getByName(request.getSearchQuery()));
                    break;
                case "time":
                    response.setPlaces(pubsRepository.getByTime(request.getSearchQuery()));
                    break;
                case "type":
                    response.setPlaces(pubsRepository.getByType(request.getSearchQuery()));
                    break;
            }
        }
        return response;
    }
}
