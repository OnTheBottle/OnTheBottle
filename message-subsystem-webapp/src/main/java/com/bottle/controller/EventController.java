package com.bottle.controller;

import com.bottle.model.DTO.*;
import com.bottle.model.DTO.validators.EventValidator;
import com.bottle.model.entity.Place;
import com.bottle.model.entity.User;
import com.bottle.service.auth.AuthService;
import com.bottle.service.event.AllEventService;
import com.bottle.service.event.NotEventException;
import com.bottle.service.place.AllPlaceService;
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
    public ResponseEntity<?> getAllEvents(@RequestBody EventRequestDTO eventRequest,
                                          @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        List<EventResponseDTO> events = allEventService.getEvents(
                eventRequest.getOptions(), eventRequest.getEventsPage(), eventRequest.getSortType(), authService.getAuthId(token));
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value = "/getEvent", method = RequestMethod.POST)
    public ResponseEntity<?> getEvent(@RequestBody IdDTO event,
                                      @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        EventResponseDTO eventResponse;
        try {
            eventResponse = allEventService.getEvent(event.getId(), authService.getAuthId(token), token);
        } catch (NotEventException e) {
            return ErrorResponse.getErrorResponse("Doesn't exist event");
        }
        return new ResponseEntity<>(eventResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/getPlaces", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPlaces(@RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        List<Place> places = allPlaceService.getAllPlaces();
        return new ResponseEntity<>(places, HttpStatus.OK);
    }

    @RequestMapping(path = "/createEvent", method = RequestMethod.POST)
    public ResponseEntity<?> createEvent(@RequestBody EventDTO event,
                                         @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        allEventService.createEvent(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/joinEvent", method = RequestMethod.POST)
    public ResponseEntity<?> addUserToEvent(@RequestBody IdDTO event,
                                            @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        boolean result = allEventService.addUser(event.getId(), authService.getAuthId(token));
        if (!result) return ErrorResponse.getErrorResponse("Closed event");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/leaveEvent", method = RequestMethod.POST)
    public ResponseEntity<?> leaveUserEvent(@RequestBody IdDTO event,
                                            @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        allEventService.deleteUser(event.getId(), authService.getAuthId(token));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/updateEvent", method = RequestMethod.POST)
    public ResponseEntity<?> updateEvent(@RequestBody EventDTO event,
                                         @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        allEventService.updateEvent(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/closeEvent", method = RequestMethod.POST)
    public ResponseEntity<?> closeEvent(@RequestBody IdDTO event,
                                        @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        allEventService.closeEvent(event.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/searchEvents", method = RequestMethod.POST)
    public ResponseEntity<?> searchEvents(@RequestBody SearchEventsDTO searchEvents,
                                          @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        List<EventResponseDTO> events = allEventService.searchEvents(searchEvents.getSearchQuery(),
                searchEvents.getEventsPage(), authService.getAuthId(token));
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(path = "/getAllUsers", method = RequestMethod.POST)
    public ResponseEntity<?> getAllUsers(@RequestBody IdDTO event,
                                                   @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        try {
            UsersDTO users = allEventService.getUsersEvent(event.getId(), authService.getAuthId(token), token);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (NotEventException e) {
            return ErrorResponse.getErrorResponse("Doesn't exist event");
        }
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
