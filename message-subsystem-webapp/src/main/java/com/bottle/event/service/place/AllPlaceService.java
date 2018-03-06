package com.bottle.event.service.place;

import com.bottle.event.model.DTO.EventDTO;
import com.bottle.event.model.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AllPlaceService {
    private GetterPlace getterPlace;

    @Autowired
    public AllPlaceService(GetterPlace getterPlace) {
        this.getterPlace = getterPlace;
    }

    public Place createOrGet(EventDTO eventDTO) { // TODO
        UUID id = eventDTO.getPlace().equals("0") ?
                UUID.randomUUID() : UUID.fromString(eventDTO.getPlace());
        return getterPlace.createOrGet(id);
    }

    public List<UUID> getAllPlaces() {
        return getterPlace.getAllId();
    }
}
