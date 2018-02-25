package com.bottle.event.controller;

import com.bottle.event.model.DTO.ListResponseDTO;
import com.bottle.event.model.DTO.StringResponseDTO;
import com.bottle.event.service.event.AllEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
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
    public StringResponseDTO createEvent() { //TODO
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("title", "Test");
        paramMap.put("text", "Test text");
        paramMap.put("start_time", "12:25 23.02.18");
        paramMap.put("end_time", "17:25 23.02.18");
        paramMap.put("idPlace", String.valueOf(UUID.randomUUID()));
        StringResponseDTO stringResponseDTO = new StringResponseDTO();
        stringResponseDTO.setResult(allEventService.registrationEvent(paramMap));
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
