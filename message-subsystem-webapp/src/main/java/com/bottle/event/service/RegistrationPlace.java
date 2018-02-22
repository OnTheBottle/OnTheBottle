package com.bottle.event.service;

import com.bottle.event.model.entity.Place;

import java.util.UUID;

public class RegistrationPlace {
    public Place registration(UUID id) { //TODO
        Place place = new Place();
        place.setId(id);
        return place;
    }
}
