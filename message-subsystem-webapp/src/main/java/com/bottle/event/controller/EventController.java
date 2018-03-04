package com.bottle.event.controller;

import com.bottle.event.model.DTO.EventDTO;
import com.bottle.event.model.DTO.request.IdRequestDTO;
import com.bottle.event.model.DTO.response.EventsResponseDTO;
import com.bottle.event.model.DTO.response.ResultResponseDTO;
import com.bottle.event.model.DTO.response.ListResponseDTO;
import com.bottle.event.model.entity.Event;
import com.bottle.event.service.event.AllEventService;
import com.bottle.event.service.place.AllPlaceService;
import com.bottle.event.service.user.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResultResponseDTO createEvent(EventDTO eventDTO) { //TODO
        ResultResponseDTO resultResponseDTO = new ResultResponseDTO();
        resultResponseDTO.setResult(allEventService.registrationEvent(eventDTO));
        return resultResponseDTO;
    }

    @PostMapping(path = "/closeEvent")
    @ResponseBody
    public ResultResponseDTO deleteEvent(EventDTO eventDTO) { //TODO
        ResultResponseDTO resultResponseDTO = new ResultResponseDTO();
        resultResponseDTO.setResult(allEventService.deleteEvent(eventDTO));
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
    public ListResponseDTO showAllPlaces() { //TODO
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

        return eventDTO;
    }

    @PostMapping(path = "/createUser")
    @ResponseBody
    public ResultResponseDTO createUser() { //TODO
        ResultResponseDTO resultResponseDTO = new ResultResponseDTO();
        resultResponseDTO.setResult(allUserService.registrationUser());
        return resultResponseDTO;
    }

    @PostMapping(path = "/showAllUsers")
    @ResponseBody
    public ListResponseDTO showAllUsers() { //TODO
        ListResponseDTO<UUID> listResponseDTO = new ListResponseDTO<>();
        listResponseDTO.setList(allUserService.getAllUsers());

        return listResponseDTO;
    }
}
