package com.bottle.service.place;

import com.bottle.model.DTO.EventDTO;
import com.bottle.model.DTO.request.IdRequestDTO;
import com.bottle.model.entity.Event;
import com.bottle.model.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AllPlaceService {
    private GetterPlace getterPlace;

    @Autowired
    public AllPlaceService(GetterPlace getterPlace) {
        this.getterPlace = getterPlace;
    }

    public Place getPlace(UUID id) {
        return getterPlace.getPlace(id);
    }

    public Place createPlace() {
        return getterPlace.createPlace(UUID.randomUUID());
    }

    public List<UUID> getAllPlaces() {
        return getterPlace.getAllId();
    }

    public Map<UUID, String> getAllEventsIdInPlace(UUID id) {
        Map<UUID, String> allEvents = new HashMap<>();
        Place place = getterPlace.getPlace(id);

        for (Event event : place.getEvents()) {
            allEvents.put(event.getId(), event.getTitle());
        }

        return allEvents;
    }
}
