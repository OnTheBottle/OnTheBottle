package com.bottle.event.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AllEventService {
    private RegistrationEvent registrationEvent;

    @Autowired
    public AllEventService(RegistrationEvent registrationEvent) {
        this.registrationEvent = registrationEvent;
    }

    public String registrationEvent(Map<String, String> paramMap) {
        return registrationEvent.createAndSave(paramMap);
    }
}
