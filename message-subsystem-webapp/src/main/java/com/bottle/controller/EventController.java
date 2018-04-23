package com.bottle.controller;

import com.bottle.model.DTO.EventDTO;
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

import java.util.List;
import java.util.Set;
import java.util.UUID;

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

    @RequestMapping(value = "/getEvents", params = "user_Id", method = RequestMethod.GET)
    public ResponseEntity<Set<Event>> showAllEvents(@RequestParam("user_Id") UUID id) {
        Set<Event> events = allEventService.getAllEventsFromUser(id);
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
        return new ResponseEntity<>( HttpStatus.OK );
    }

    /*
    @PostMapping(path = "/closeEvent")
    @ResponseBody
    public ResultResponseDTO deleteEvent(IdRequestDTO idRequestDTO) {
        ResultResponseDTO resultResponseDTO = new ResultResponseDTO();
        resultResponseDTO.setResult(allEventService.closeEvent(idRequestDTO.getId()));
        return resultResponseDTO;
    }

    @PostMapping(path = "/showInfoEvent")
    @ResponseBody
    public EventDTO showInfoEvent(IdRequestDTO idRequestDTO) {
        EventDTO eventDTO = new EventDTO();
        Event event = allEventService.getEvent(idRequestDTO.getId());

        eventDTO.setTitle(event.getTitle());
        eventDTO.setText(event.getText());
        eventDTO.setStartTime(String.valueOf(event.getStartTime()).replace(' ', 'T'));
        eventDTO.setEndTime(String.valueOf(event.getEndTime()).replace(' ', 'T'));
        eventDTO.setPlace(event.getPlace().getId());
        eventDTO.setOwner(event.getOwner().getId());

        List<UUID> uuids = new ArrayList<>();
        for (User user : event.getUsers()) {
            uuids.add(user.getId());
        }
        eventDTO.setUsers(uuids);

        return eventDTO;
    }

    @PostMapping(path = "/createPlace")
    @ResponseBody
    public ResultResponseDTO createPlace() {
        ResultResponseDTO resultResponseDTO = new ResultResponseDTO();
        resultResponseDTO.setResult(String.valueOf(allPlaceService.createPlace().getId()));
        return resultResponseDTO;
    }

    @PostMapping(path = "/showAllUsers")
    @ResponseBody
    public ListResponseDTO<UUID> showAllUsers() {
        ListResponseDTO<UUID> listResponseDTO = new ListResponseDTO<>();
        listResponseDTO.setList(allUserService.getAllUsersId());

        return listResponseDTO;
    }

    @PostMapping(path = "/addUserToEvent")
    @ResponseBody
    public ResultResponseDTO addUserToEvent(IdEventAndUserRequestDTO idEventAndUserRequestDTO) {
        ResultResponseDTO resultResponseDTO = new ResultResponseDTO();
        UUID idUser = idEventAndUserRequestDTO.getIdUser();
        UUID idEvent = idEventAndUserRequestDTO.getIdEvent();
        resultResponseDTO.setResult(allEventService.addUser(idEvent, idUser));

        return resultResponseDTO;
    }

    @PostMapping(path = "/deleteUserFromEvent")
    @ResponseBody
    public ResultResponseDTO deleteUserFromEvent(IdEventAndUserRequestDTO idEventAndUserRequestDTO) {
        ResultResponseDTO resultResponseDTO = new ResultResponseDTO();
        UUID idUser = idEventAndUserRequestDTO.getIdUser();
        UUID idEvent = idEventAndUserRequestDTO.getIdEvent();
        resultResponseDTO.setResult(allEventService.deleteUser(idEvent, idUser));

        return resultResponseDTO;
    }

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
