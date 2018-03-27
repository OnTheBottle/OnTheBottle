package com.bottle.event.service.place;

import com.bottle.event.model.DTO.EventDTO;
import com.bottle.event.model.DTO.request.IdRequestDTO;
import com.bottle.event.model.entity.Event;
import com.bottle.event.model.entity.Place;
import com.bottle.event.model.entity.User;
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

    public Place createOrGet(EventDTO eventDTO) { // TODO
        return getterPlace.createOrGet(eventDTO.getPlace());
    }

    public List<UUID> getAllPlaces() {
        return getterPlace.getAllId();
    }

    public Map<UUID, String> getAllEventsId(IdRequestDTO idRequestDTO) {
        Map<UUID, String> allEvents = new HashMap<>();
        Place place = getterPlace.createOrGet(idRequestDTO.getId());

        for (Event event : place.getEvents()) {
            allEvents.put(event.getId(), event.getTitle());
        }

        return allEvents;
    }
}
