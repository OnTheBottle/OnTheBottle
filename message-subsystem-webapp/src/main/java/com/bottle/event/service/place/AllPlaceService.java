package com.bottle.event.service.place;

import com.bottle.event.model.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class AllPlaceService {
    private RegistrationPlace registrationPlace;

    @Autowired
    public AllPlaceService(RegistrationPlace registrationPlace) {
        this.registrationPlace = registrationPlace;
    }

    public Place createOrGet(Map<String, String> paramMap) {
        UUID id = UUID.fromString(paramMap.get("idPlace"));
        return registrationPlace.createOrGet(id);
    }
}
