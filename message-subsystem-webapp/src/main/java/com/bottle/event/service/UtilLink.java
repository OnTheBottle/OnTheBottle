package com.bottle.event.service;

import com.bottle.event.model.entity.Event;
import com.bottle.event.model.entity.Place;
import com.bottle.event.model.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilLink {
    private PlaceRepository placeRepository;

    @Autowired
    public UtilLink(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public void linkEventAndPlace(Event event) {
        Place place = event.getPlace();
        place.getEvents().add(event);
        placeRepository.save(place);
    }
}
