package com.bottle.service.event;

import com.bottle.model.DTO.EventDTO;
import com.bottle.model.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class AllEventService {
    private RegistrationEvent registrationEvent;
    private GetterEvent getterEvent;
    private CloseEvent closeEvent;
    private EntityBinder entityBinder;

    @Autowired
    public AllEventService(RegistrationEvent registrationEvent, GetterEvent getterEvent, CloseEvent closeEvent, EntityBinder entityBinder) {
        this.registrationEvent = registrationEvent;
        this.getterEvent = getterEvent;
        this.closeEvent = closeEvent;
        this.entityBinder = entityBinder;
    }

    public String registrationEvent(EventDTO eventDTO) {
        return registrationEvent.createEvent(eventDTO);
    }

    public String closeEvent(UUID id) {
        return closeEvent.closeEvent(id);
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

    public String addUser(UUID idEvent, UUID idUser) {
        return entityBinder.addUserToEvent(idEvent, idUser);
    }

    public String deleteUser(UUID idEvent, UUID idUser) {
        return entityBinder.deleteUserFromEvent(idEvent, idUser);
    }
}
