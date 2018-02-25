package com.bottle.event.controller;

import com.bottle.event.model.DTO.ResponseDTO;
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

    @PostMapping(path = "/doGame")
    @ResponseBody
    public ResponseDTO createEvent() { //TODO
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("title", "Test");
        paramMap.put("text", "Test text");
        paramMap.put("start_time", "12:25 23.02.18");
        paramMap.put("end_time", "17:25 23.02.18");
        paramMap.put("idPlace", String.valueOf(UUID.randomUUID()));
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResult(allEventService.registrationEvent(paramMap));
        return responseDTO;
    }
}
