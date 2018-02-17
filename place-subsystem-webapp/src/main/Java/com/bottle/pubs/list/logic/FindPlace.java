package com.bottle.pubs.list.logic;

import com.bottle.pubs.list.repository.PubsRepository;
import com.bottle.pubs.list.request.Request;
import com.bottle.pubs.list.response.Response;

public class FindPlace {
    public Response doSearch(PubsRepository pubsRepository, Request request) {
        Response response = new Response();
        if (request.isShowAll()) {
            response.setPlaces(pubsRepository.getAll());
        } else {
            switch (request.getSearchType()){
                case "Name" : response.setPlaces(pubsRepository.getByName(request.getSearchQuery())); break;
                case "Time" : response.setPlaces(pubsRepository.getByTime(request.getSearchQuery())); break;
                case "Type" : response.setPlaces(pubsRepository.getByType(request.getSearchQuery())); break;
            }
        }
        return response;
    }
}
