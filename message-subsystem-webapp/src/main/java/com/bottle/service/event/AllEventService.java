package com.bottle.service.event;

import com.bottle.model.DTO.EventDTO;
import com.bottle.model.DTO.request.IdEventAndUserRequestDTO;
import com.bottle.model.DTO.request.IdRequestDTO;
import com.bottle.model.entity.Event;
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

    public Event getEvent(UUID id) {
        return getterEvent.getEvent(id);
    }

    public String addUser(IdEventAndUserRequestDTO idEventAndUserRequestDTO) {
        return entityBinder.addUserToEvent(idEventAndUserRequestDTO);
    }

    public String deleteUser(IdEventAndUserRequestDTO idEventAndUserRequestDTO) {
        return entityBinder.deleteUserFromEvent(idEventAndUserRequestDTO);
    }
}
