package com.bottle.event.service.event;

import com.bottle.event.model.DTO.request.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllEventService {
    private RegistrationEvent registrationEvent;
    private GetterEvent getterEvent;

    @Autowired
    public AllEventService(RegistrationEvent registrationEvent, GetterEvent getterEvent) {
        this.registrationEvent = registrationEvent;
        this.getterEvent = getterEvent;
    }

    public String registrationEvent(EventDTO eventDTO) {
        return registrationEvent.createAndSave(eventDTO);
    }

    public List<String> getAllEvents() {
        return getterEvent.getAllEvents();
    }
}
