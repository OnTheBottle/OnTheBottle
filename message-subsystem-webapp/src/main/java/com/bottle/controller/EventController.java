package com.bottle.controller;

import com.bottle.model.DTO.IdDTO;
import com.bottle.model.DTO.Request.EventDTO;
import com.bottle.model.DTO.Request.EventRequestDTO;
import com.bottle.model.DTO.Request.SearchEventsDTO;
import com.bottle.model.DTO.Response.EventResponseDTO;
import com.bottle.model.DTO.UsersDTO;
import com.bottle.model.entity.Place;
import com.bottle.model.repository.PlaceRepository;
import com.bottle.service.auth.AuthService;
import com.bottle.service.event.EventService;
import com.bottle.service.event.NotEventException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class EventController {
    private EventService eventService;
    private PlaceRepository placeRepository;
    private AuthService authService;

    @Autowired
    public EventController(EventService eventService, PlaceRepository placeRepository, AuthService authService) {
        this.eventService = eventService;
        this.placeRepository = placeRepository;
        this.authService = authService;
    }

    @RequestMapping(value = "/getEvents", method = RequestMethod.POST)
    public ResponseEntity<?> getAllEvents(@RequestBody EventRequestDTO eventRequest,
                                          @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        List<EventResponseDTO> events = eventService.getEvents(
                eventRequest.getOptions(), eventRequest.getEventsPage(), eventRequest.getSortType(), authService.getAuthId(token));
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value = "/getEvent", method = RequestMethod.POST)
    public ResponseEntity<?> getEvent(@RequestBody IdDTO event,
                                      @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        EventResponseDTO eventResponse;
        try {
            eventResponse = eventService.getEvent(event.getId(), authService.getAuthId(token), token);
        } catch (NotEventException e) {
            return ErrorResponse.getErrorResponse("Doesn't exist event");
        }
        return new ResponseEntity<>(eventResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/getPlaces", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPlaces(@RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        List<Place> places = placeRepository.findAll();
        return new ResponseEntity<>(places, HttpStatus.OK);
    }

    @RequestMapping(path = "/createEvent", method = RequestMethod.POST)
    public ResponseEntity<?> createEvent(@RequestBody EventDTO event,
                                         @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        eventService.createEvent(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/joinEvent", method = RequestMethod.POST)
    public ResponseEntity<?> addUserToEvent(@RequestBody IdDTO event,
                                            @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        boolean result = eventService.addUser(event.getId(), authService.getAuthId(token));
        if (!result) return ErrorResponse.getErrorResponse("Closed event");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/leaveEvent", method = RequestMethod.POST)
    public ResponseEntity<?> leaveUserEvent(@RequestBody IdDTO event,
                                            @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        boolean result = eventService.deleteUser(event.getId(), authService.getAuthId(token));
        if (!result) return ErrorResponse.getErrorResponse("Closed event");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/updateEvent", method = RequestMethod.POST)
    public ResponseEntity<?> updateEvent(@RequestBody EventDTO event,
                                         @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        eventService.updateEvent(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/closeEvent", method = RequestMethod.POST)
    public ResponseEntity<?> closeEvent(@RequestBody IdDTO event,
                                        @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        eventService.closeEvent(event.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/searchEvents", method = RequestMethod.POST)
    public ResponseEntity<?> searchEvents(@RequestBody SearchEventsDTO searchEvents,
                                          @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        List<EventResponseDTO> events = eventService.searchEvents(searchEvents.getSearchQuery(),
                searchEvents.getEventsPage(), authService.getAuthId(token));
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(path = "/getAllUsers", method = RequestMethod.POST)
    public ResponseEntity<?> getAllUsers(@RequestBody IdDTO event,
                                         @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        UsersDTO users = eventService.getUsersEvent(event.getId(), authService.getAuthId(token), token);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(path = "/getEventsFromUser", method = RequestMethod.POST)
    public ResponseEntity<?> getEventsFromUser(@RequestBody IdDTO event,
                                         @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        List<EventResponseDTO> events = eventService.getEventsFromUser(event.getId());
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
