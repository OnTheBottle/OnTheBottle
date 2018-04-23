package com.bottle.service.place;

import com.bottle.model.entity.Event;
import com.bottle.model.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Random random = new Random();
        UUID id = UUID.randomUUID();
        String title = "testTitle" + random.nextInt(100);
        String avatar = "";
        return getterPlace.createPlace(id, title, avatar);
    }

    public List<Place> getAllPlaces() {
        return getterPlace.getAll();
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
