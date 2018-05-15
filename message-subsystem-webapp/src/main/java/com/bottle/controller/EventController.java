package com.bottle.controller;

import com.bottle.model.DTO.EventDTO;
import com.bottle.model.DTO.EventResponseDTO;
import com.bottle.model.DTO.UserEventDTO;
import com.bottle.model.DTO.RequestEventDTO;
import com.bottle.model.DTO.validators.EventValidator;
import com.bottle.model.entity.Event;
import com.bottle.model.entity.Place;
import com.bottle.service.auth.AuthService;
import com.bottle.service.event.AllEventService;
import com.bottle.service.place.AllPlaceService;
import com.bottle.service.user.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    private EventValidator eventValidator;
    private AuthService authService;

    @Autowired
    public EventController(AllEventService allEventService, AllPlaceService allPlaceService, AuthService authService, EventValidator eventValidator) {
        this.allEventService = allEventService;
        this.allPlaceService = allPlaceService;
        this.authService = authService;
        this.eventValidator = eventValidator;
    }

    @RequestMapping(value = "/getEvents", method = RequestMethod.POST)
    public ResponseEntity<?> showAllEvents(@RequestBody RequestEventDTO requestEventDTO,
                                           @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return getNonValidTokenResponse();

        Set<EventResponseDTO> events = allEventService.getEvents(requestEventDTO);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value = "/getPlaces", method = RequestMethod.GET)
    public ResponseEntity<?> showAllPlaces(@RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return getNonValidTokenResponse();

        List<Place> places = allPlaceService.getAllPlaces();
        return new ResponseEntity<>(places, HttpStatus.OK);
    }

    @RequestMapping(path = "/createEvent", method = RequestMethod.POST)
    public ResponseEntity<?> savePost(@RequestBody EventDTO eventDTO,
                                         @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return getNonValidTokenResponse();

        allEventService.createEvent(eventDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/joinEvent", method = RequestMethod.POST)
    public ResponseEntity<?> addUserToEvent(@RequestBody UserEventDTO userEventDTO,
                                               @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return getNonValidTokenResponse();

        String result = allEventService.addUser(userEventDTO.getEventId(), userEventDTO.getUserId());
        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.OK);
        if (result.equals("Closed")) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @RequestMapping(path = "/leaveEvent", method = RequestMethod.POST)
    public ResponseEntity<?> leaveUserEvent(@RequestBody UserEventDTO userEventDTO,
                                               @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return getNonValidTokenResponse();

        allEventService.deleteUser(userEventDTO.getEventId(), userEventDTO.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/updateEvent", method = RequestMethod.POST)
    public ResponseEntity<?> updateEvent(@RequestBody EventDTO eventDTO,
                                            @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return getNonValidTokenResponse();

        allEventService.updateEvent(eventDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/closeEvent", method = RequestMethod.POST)
    public ResponseEntity<?> closeEvent(@RequestBody EventDTO eventDTO,
                                           @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return getNonValidTokenResponse();

        allEventService.closeEvent(eventDTO.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<String> getNonValidTokenResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "text/plain");
        String message = "Non-valid token";
        return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
    }
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
