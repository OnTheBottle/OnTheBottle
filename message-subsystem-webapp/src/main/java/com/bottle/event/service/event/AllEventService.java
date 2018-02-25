package com.bottle.event.service.event;

import com.bottle.event.model.DTO.request.EventRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class AllEventService {
    private RegistrationEvent registrationEvent;
    private GetterEvent getterEvent;

    @Autowired
    public AllEventService(RegistrationEvent registrationEvent, GetterEvent getterEvent) {
        this.registrationEvent = registrationEvent;
        this.getterEvent = getterEvent;
    }

    public String registrationEvent(EventRequestDTO eventDTO) {
        return registrationEvent.createAndSave(eventDTO);
    }

    public Map<UUID, String> getAllEvents() {
        return getterEvent.getAllEvents();
    }
}
