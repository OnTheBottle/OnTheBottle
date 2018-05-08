package com.bottle.controller;

import com.bottle.model.DTO.EventDTO;
import com.bottle.model.DTO.UserEventDTO;
import com.bottle.model.DTO.RequestEventDTO;
import com.bottle.model.DTO.validators.EventValidator;
import com.bottle.model.entity.Event;
import com.bottle.model.entity.Place;
import com.bottle.service.event.AllEventService;
import com.bottle.service.place.AllPlaceService;
import com.bottle.service.user.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@Controller
public class EventController {
    private AllEventService allEventService;
    private AllPlaceService allPlaceService;
    private AllUserService allUserService;
    private EventValidator eventValidator;

    @Autowired
    public EventController(AllEventService allEventService, AllPlaceService allPlaceService, AllUserService allUserService, EventValidator eventValidator) {
        this.allEventService = allEventService;
        this.allPlaceService = allPlaceService;
        this.allUserService = allUserService;
        this.eventValidator = eventValidator;
    }

    @RequestMapping(value = "/getEvents", method = RequestMethod.POST)
    public ResponseEntity<Set<Event>> showAllEvents(@RequestBody RequestEventDTO requestEventDTO) {
        Set<Event> events = allEventService.getEvents(requestEventDTO);
        if (events.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value = "/getPlaces", method = RequestMethod.GET)
    public ResponseEntity<List<Place>> showAllPlaces() {
        List<Place> places = allPlaceService.getAllPlaces();
        if (places.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(places, HttpStatus.OK);
    }

    @RequestMapping(path = "/createEvent", method = RequestMethod.POST)
    public ResponseEntity<Void> savePost(@RequestBody EventDTO eventDTO) {
        allEventService.createEvent(eventDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/joinEvent", method = RequestMethod.POST)
    public ResponseEntity<Void> addUserToEvent(@RequestBody UserEventDTO userEventDTO) {
        String result = allEventService.addUser(userEventDTO.getEventId(), userEventDTO.getUserId());

        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.OK);
        if (result.equals("Closed")) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @RequestMapping(path = "/leaveEvent", method = RequestMethod.POST)
    public ResponseEntity<Void> leaveUserEvent(@RequestBody UserEventDTO userEventDTO) {
        allEventService.deleteUser(userEventDTO.getEventId(), userEventDTO.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/updateEvent", method = RequestMethod.POST)
    public ResponseEntity<Void> updateEvent(@RequestBody EventDTO eventDTO) {
        allEventService.updateEvent(eventDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/closeEvent", method = RequestMethod.POST)
    public ResponseEntity<Void> closeEvent(@RequestBody EventDTO eventDTO) {
        allEventService.closeEvent(eventDTO.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    @PostMapping(path = "/showEventsFromUser")
    @ResponseBody
    public EventsResponseDTO showEventsFromUser(IdRequestDTO idRequestDTO) {
        EventsResponseDTO eventListDTO = new EventsResponseDTO();
        eventListDTO.setActiveEvents(allUserService.getAllEventsIdFromUser(idRequestDTO.getId()));

        return eventListDTO;
    }

    @PostMapping(path = "/showEventsFromPlace")
    @ResponseBody
    public EventsResponseDTO showEventsFromPlace(IdRequestDTO idRequestDTO) {
        EventsResponseDTO eventListDTO = new EventsResponseDTO();
        eventListDTO.setActiveEvents(allPlaceService.getAllEventsIdInPlace(idRequestDTO.getId()));

        return eventListDTO;
    }*/
}
