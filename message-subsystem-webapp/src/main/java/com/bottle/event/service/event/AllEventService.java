package com.bottle.event.service.event;

import com.bottle.event.model.DTO.EventDTO;
import com.bottle.event.model.DTO.request.IdEventAndUserRequestDTO;
import com.bottle.event.model.DTO.request.IdRequestDTO;
import com.bottle.event.model.entity.Event;
import com.bottle.event.service.EntityBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class AllEventService {
    private RegistrationEvent registrationEvent;
    private GetterEvent getterEvent;
    private DeleteEvent deleteEvent;
    private EntityBinder entityBinder;

    @Autowired
    public AllEventService(RegistrationEvent registrationEvent, GetterEvent getterEvent, DeleteEvent deleteEvent, EntityBinder entityBinder) {
        this.registrationEvent = registrationEvent;
        this.getterEvent = getterEvent;
        this.deleteEvent = deleteEvent;
        this.entityBinder = entityBinder;
    }

    public String registrationEvent(EventDTO eventDTO) {
        return registrationEvent.createAndSave(eventDTO);
    }

    public String closeEvent(IdRequestDTO idRequestDTO) {
        return deleteEvent.closeEvent(idRequestDTO);
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

    public String addUser(IdEventAndUserRequestDTO idEventAndUserRequestDTO) {
        return entityBinder.addUserToEvent(idEventAndUserRequestDTO);
    }
}
