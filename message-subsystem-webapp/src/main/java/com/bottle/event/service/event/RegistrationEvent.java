package com.bottle.event.service.event;

import com.bottle.event.model.entity.Event;
import com.bottle.event.model.entity.Place;
import com.bottle.event.model.repository.EventStore;
import com.bottle.event.model.repository.PlaceStore;
import com.bottle.event.service.place.AllPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service
public class RegistrationEvent {
    private BuildEvent buildEvent;
    private EventStore eventStore;
    private PlaceStore placeStore;
    private AllPlaceService placeService;

    @Autowired
    public RegistrationEvent(
            BuildEvent buildEvent, EventStore eventStore, PlaceStore placeStore,
            AllPlaceService placeService) {
        this.buildEvent = buildEvent;
        this.eventStore = eventStore;
        this.placeStore = placeStore;
        this.placeService = placeService;
    }

    public String createAndSave(Map<String, String> paramMap) {
        Event event = buildEvent.build(paramMap);
        Place place = placeService.createOrGet(paramMap);
        event.setPlace(place);

        String result;
        try {
            eventStore.createOrUpdate(event);
            placeStore.createOrUpdate(place);
            result = "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            result = "error";
        }

        return result;
    }
}
