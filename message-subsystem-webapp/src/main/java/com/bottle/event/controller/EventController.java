package com.bottle.event.controller;

import com.bottle.event.model.DTO.EventDTO;
import com.bottle.event.model.DTO.request.IdEventAndUserRequestDTO;
import com.bottle.event.model.DTO.request.IdRequestDTO;
import com.bottle.event.model.DTO.response.EventsResponseDTO;
import com.bottle.event.model.DTO.response.ListResponseDTO;
import com.bottle.event.model.DTO.response.ResultResponseDTO;
import com.bottle.event.model.entity.Event;
import com.bottle.event.model.entity.User;
import com.bottle.event.service.event.AllEventService;
import com.bottle.event.service.place.AllPlaceService;
import com.bottle.event.service.user.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class EventController {
    private AllEventService allEventService;
    private AllPlaceService allPlaceService;
    private AllUserService allUserService;

    @Autowired
    public EventController(AllEventService allEventService, AllPlaceService allPlaceService, AllUserService allUserService) {
        this.allEventService = allEventService;
        this.allPlaceService = allPlaceService;
        this.allUserService = allUserService;
    }

    @PostMapping(path = "/saveEvent")
    @ResponseBody
    public ResponseEntity createEvent(@RequestBody @Valid EventDTO eventDTO, BindingResult bindingResult ) { //TODO
        ResponseEntity responseEntity;

        if (bindingResult.hasErrors()) {
            responseEntity = ResponseEntity.badRequest().body("LoL");
            System.out.println("bindingResult");
        } else {
            responseEntity = ResponseEntity.ok(allEventService.registrationEvent(eventDTO));
            System.out.println("!bindingResult");
        }

        return responseEntity;
    }

    @PostMapping(path = "/closeEvent")
    @ResponseBody
    public ResultResponseDTO deleteEvent(IdRequestDTO idRequestDTO) { //TODO
        ResultResponseDTO resultResponseDTO = new ResultResponseDTO();
        resultResponseDTO.setResult(allEventService.closeEvent(idRequestDTO));
        return resultResponseDTO;
    }

    @PostMapping(path = "/showAllEvents")
    @ResponseBody
    public EventsResponseDTO showAllEvents() { //TODO
        EventsResponseDTO eventListDTO = new EventsResponseDTO();
        eventListDTO.setActiveEvents(allEventService.getAllActiveEventsId());
        eventListDTO.setPassedEvents(allEventService.getAllPassedEventsId());

        return eventListDTO;
    }

    @PostMapping(path = "/showAllPlaces")
    @ResponseBody
    public ListResponseDTO<UUID> showAllPlaces() { //TODO
        ListResponseDTO<UUID> placesResponseDTO = new ListResponseDTO<>();
        placesResponseDTO.setList(allPlaceService.getAllPlaces());

        return placesResponseDTO;
    }

    @PostMapping(path = "/showInfoEvent")
    @ResponseBody
    public EventDTO showInfoEvent(IdRequestDTO idRequestDTO) { //TODO
        EventDTO eventDTO = new EventDTO();
        Event event = allEventService.getEvent(idRequestDTO.getId());

        eventDTO.setTitle(event.getTitle());
        eventDTO.setText(event.getText());
        eventDTO.setStartTime(String.valueOf(event.getStartTime()).replace(' ', 'T'));
        eventDTO.setEndTime(String.valueOf(event.getEndTime()).replace(' ', 'T'));
        eventDTO.setPlace(String.valueOf(event.getPlace().getId()));
        eventDTO.setOwner(String.valueOf(event.getOwner().getId()));

        List<UUID> uuids = new ArrayList<>();
        for (User user : event.getUsers()) {
            uuids.add(user.getId());
        }
        eventDTO.setUsers(uuids);

        return eventDTO;
    }

    @PostMapping(path = "/createUser")
    @ResponseBody
    public ResultResponseDTO createUser() { //TODO
        ResultResponseDTO resultResponseDTO = new ResultResponseDTO();
        resultResponseDTO.setResult(String.valueOf(allUserService.createOrGet().getId()));
        return resultResponseDTO;
    }

    @PostMapping(path = "/showAllUsers")
    @ResponseBody
    public ListResponseDTO<UUID> showAllUsers() { //TODO
        ListResponseDTO<UUID> listResponseDTO = new ListResponseDTO<>();
        listResponseDTO.setList(allUserService.getAllUsers());

        return listResponseDTO;
    }

    @PostMapping(path = "/addUserToEvent")
    @ResponseBody
    public ResultResponseDTO addUserToEvent(IdEventAndUserRequestDTO idEventAndUserRequestDTO) { //TODO
        ResultResponseDTO resultResponseDTO = new ResultResponseDTO();
        resultResponseDTO.setResult(allEventService.addUser(idEventAndUserRequestDTO));

        return resultResponseDTO;
    }

    @PostMapping(path = "/deleteUserFromEvent")
    @ResponseBody
    public ResultResponseDTO deleteUserFromEvent(IdEventAndUserRequestDTO idEventAndUserRequestDTO) { //TODO
        ResultResponseDTO resultResponseDTO = new ResultResponseDTO();
        resultResponseDTO.setResult(allEventService.deleteUser(idEventAndUserRequestDTO));

        return resultResponseDTO;
    }

    @PostMapping(path = "/showEventsFromUser")
    @ResponseBody
    public EventsResponseDTO showEventsFromUser(IdRequestDTO idRequestDTO) { //TODO
        EventsResponseDTO eventListDTO = new EventsResponseDTO();
        eventListDTO.setActiveEvents(allUserService.getAllEventsId(idRequestDTO));

        return eventListDTO;
    }

    @PostMapping(path = "/showEventsFromPlace")
    @ResponseBody
    public EventsResponseDTO showEventsFromPlace(IdRequestDTO idRequestDTO) { //TODO
        EventsResponseDTO eventListDTO = new EventsResponseDTO();
        eventListDTO.setActiveEvents(allPlaceService.getAllEventsId(idRequestDTO));

        return eventListDTO;
    }
}