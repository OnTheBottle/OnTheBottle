package com.bottle.service.event;

import com.bottle.model.DTO.EventDTO;
import com.bottle.model.entity.Event;
import com.bottle.model.entity.Place;
import com.bottle.model.entity.User;
import com.bottle.service.place.AllPlaceService;
import com.bottle.service.user.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationEvent {
    private BuildEvent buildEvent;
    private AllPlaceService placeService;
    private AllUserService userService;
    private EntityBinder entityBinder;

    @Autowired
    public RegistrationEvent(
            BuildEvent buildEvent, AllPlaceService placeService, AllUserService userService, EntityBinder entityBinder) {
        this.buildEvent = buildEvent;
        this.placeService = placeService;
        this.userService = userService;
        this.entityBinder = entityBinder;
    }

    public void createEvent(EventDTO eventDTO) {
        Event event = buildEvent.build(eventDTO);
        Place place = placeService.getPlace(eventDTO.getPlace());
        User owner = userService.getUser(eventDTO.getOwner());

        event.setPlace(place);
        event.setOwner(owner);

        entityBinder.addUserToEvent(event, owner);
    }
}

