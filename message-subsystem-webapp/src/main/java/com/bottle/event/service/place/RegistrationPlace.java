package com.bottle.event.service.place;

import com.bottle.event.model.entity.Place;
import com.bottle.event.model.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrationPlace {
    private PlaceRepository placeRepository;

    @Autowired
    public RegistrationPlace(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Place createOrGet(UUID id) {
        if (placeRepository.exists(id)) {
            return placeRepository.getOne(id);
        } else {
            Place place = new Place();
            place.setId(id);
            placeRepository.save(place);
            return place;
        }
    }
}
