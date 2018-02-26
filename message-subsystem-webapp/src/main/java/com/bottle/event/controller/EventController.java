package com.bottle.event.controller;

import com.bottle.event.model.DTO.request.EventRequestDTO;
import com.bottle.event.model.DTO.response.EventsResponseDTO;
import com.bottle.event.model.DTO.response.StringResponseDTO;
import com.bottle.event.service.event.AllEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.UUID;

@Controller
public class EventController {
    private AllEventService allEventService;

    @Autowired
    public EventController(AllEventService allEventService) {
        this.allEventService = allEventService;
    }

    @PostMapping(path = "/saveEvent")
    @ResponseBody
    public StringResponseDTO createEvent(EventRequestDTO eventDTO) { //TODO
        eventDTO.setPlace(String.valueOf(UUID.randomUUID()));
        StringResponseDTO stringResponseDTO = new StringResponseDTO();
        stringResponseDTO.setResult(allEventService.registrationEvent(eventDTO));
        return stringResponseDTO;
    }

    @PostMapping(path = "/showAllEvents")
    @ResponseBody
    public EventsResponseDTO showAllEvents() { //TODO
        EventsResponseDTO eventListDTO = new EventsResponseDTO();
        Map<UUID, String> allEvents = allEventService.getAllEvents();
        eventListDTO.setEvents(allEvents);

        return eventListDTO;
    }
}
