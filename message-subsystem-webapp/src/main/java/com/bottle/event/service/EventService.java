package com.bottle.event.service;

import java.util.Map;

public class EventService {
    private static final RegistrationEvent REGISTRATION_EVENT = new RegistrationEvent();
    private static final UpdateEvent UPDATE_EVENT = new UpdateEvent();
    private static final DeleteEvent DELETE_EVENT = new DeleteEvent();

    public String registrationEvent(Map<String, String> paramMap) {
        return REGISTRATION_EVENT.buildAndSave(paramMap);
    }

    public String updateEvent(Map<String, String> paramMap) {
        return UPDATE_EVENT.updateEvent(paramMap);
    }

    public String deleteEvent(Map<String, String> paramMap) {
        return DELETE_EVENT.deleteEvent(paramMap);
    }
}
