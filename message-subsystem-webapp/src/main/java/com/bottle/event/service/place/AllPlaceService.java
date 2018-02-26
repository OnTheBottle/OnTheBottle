package com.bottle.event.service.place;

import com.bottle.event.model.DTO.EventDTO;
import com.bottle.event.model.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AllPlaceService {
    private RegistrationPlace registrationPlace;

    @Autowired
    public AllPlaceService(RegistrationPlace registrationPlace) {
        this.registrationPlace = registrationPlace;
    }

    public void createOrUpdate(Place place) {
        registrationPlace.save(place);
    }

    public Place createOrGet(EventDTO eventDTO) {
        UUID id = UUID.fromString(eventDTO.getPlace());
        return registrationPlace.createOrGet(id);
    }
}
