package com.bottle.event.service.event;

import com.bottle.event.model.DTO.EventDTO;
import com.bottle.event.model.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class AllEventService {
    private RegistrationEvent registrationEvent;
    private GetterEvent getterEvent;
    private DeleteEvent deleteEvent;

    @Autowired
    public AllEventService(RegistrationEvent registrationEvent, GetterEvent getterEvent, DeleteEvent deleteEvent) {
        this.registrationEvent = registrationEvent;
        this.getterEvent = getterEvent;
        this.deleteEvent = deleteEvent;
    }

    public String registrationEvent(EventDTO eventDTO) {
        return registrationEvent.createAndSave(eventDTO);
    }

    public String deleteEvent(EventDTO eventDTO) {
        return deleteEvent.deleteEvent(eventDTO);
    }

    public Map<UUID, String> getAllActiveEventsId() {
        return getterEvent.getAllActiveIdAndTitle();
    }

    public Map<UUID, String> getAllPassedEventsId() {
        return getterEvent.getAllPassedIdAndTitle();
    }

    public Event getEvent(String id) {
        return getterEvent.getEvent(id);
    }
}
