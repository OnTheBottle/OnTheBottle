package com.bottle.event.controller;

import com.bottle.event.service.EventService;

import java.util.HashMap;
import java.util.Map;

public class EventController {
    private static final EventService EVENT_SERVICE = new EventService();

    public void createEvent() { //TODO
        Map<String, String> paramMap = new HashMap<>();
        String result = EVENT_SERVICE.registrationEvent(paramMap);
        System.out.println(result);
    }

    public void deletEvent() { //TODO
        Map<String, String> paramMap = new HashMap<>();
        String result = EVENT_SERVICE.deleteEvent(paramMap);
        System.out.println(result);
    }

    public void updateEvent() { //TODO
        Map<String, String> paramMap = new HashMap<>();
        String result = EVENT_SERVICE.updateEvent(paramMap);
        System.out.println(result);
    }
}
