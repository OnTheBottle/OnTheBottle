package com.bottle.event.service.event;

import com.bottle.event.model.DTO.EventDTO;
import com.bottle.event.model.entity.Event;
import com.bottle.event.model.entity.Place;
import com.bottle.event.model.repository.EventStore;
import com.bottle.event.service.place.AllPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class RegistrationEvent {
    private BuildEvent buildEvent;
    private EventStore eventStore;
    private AllPlaceService placeService;

    @Autowired
    public RegistrationEvent(
            BuildEvent buildEvent, EventStore eventStore, AllPlaceService placeService) {
        this.buildEvent = buildEvent;
        this.eventStore = eventStore;
        this.placeService = placeService;
    }

    public String createAndSave(EventDTO eventDTO) {
        Event event = buildEvent.build(eventDTO);
        Place place = placeService.createOrGet(eventDTO);
        event.setPlace(place);

        String result;
        try {
            eventStore.createOrUpdate(event);
            placeService.createOrUpdate(place);
            result = "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            result = "error";
        }

        return result;
    }
}

