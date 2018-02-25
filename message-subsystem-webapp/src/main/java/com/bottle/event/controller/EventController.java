package com.bottle.event.controller;

import com.bottle.event.model.DTO.request.EventDTO;
import com.bottle.event.model.DTO.response.ListResponseDTO;
import com.bottle.event.model.DTO.response.StringResponseDTO;
import com.bottle.event.service.event.AllEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Service
public class EventController {
    private AllEventService allEventService;

    @Autowired
    public EventController(AllEventService allEventService) {
        this.allEventService = allEventService;
    }

    @PostMapping(path = "/saveEvent")
    @ResponseBody
    public StringResponseDTO createEvent(EventDTO eventDTO) {
        eventDTO.setPlace(String.valueOf(UUID.randomUUID()));
        StringResponseDTO stringResponseDTO = new StringResponseDTO();
        stringResponseDTO.setResult(allEventService.registrationEvent(eventDTO));
        return stringResponseDTO;
    }

    @PostMapping(path = "/showAllEvents")
    @ResponseBody
    public ListResponseDTO showAllEvents() { //TODO
        ListResponseDTO listResponseDTO = new ListResponseDTO();
        listResponseDTO.setEventList(allEventService.getAllEvents());
        return listResponseDTO;
    }
}
