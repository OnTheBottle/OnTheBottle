package com.bottle.event.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AllEventService {
    private RegistrationEvent registrationEvent;
    private GetterEvent getterEvent;

    @Autowired
    public AllEventService(RegistrationEvent registrationEvent, GetterEvent getterEvent) {
        this.registrationEvent = registrationEvent;
        this.getterEvent = getterEvent;
    }

    public String registrationEvent(Map<String, String> paramMap) {
        return registrationEvent.createAndSave(paramMap);
    }

    public List<String> getAllEvents() {
        return getterEvent.getAllEvents();
    }
}
