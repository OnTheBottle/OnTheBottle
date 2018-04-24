package com.bottle.service.event;

import com.bottle.model.DTO.EventDTO;
import com.bottle.model.DTO.OptionsDTO;
import com.bottle.model.DTO.RequestEventDTO;
import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import com.bottle.service.user.GetterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AllEventService {
    private RegistrationEvent registrationEvent;
    private GetterEvent getterEvent;
    private CloseEvent closeEvent;
    private EntityBinder entityBinder;
    private GetterUser getterUser;

    @Autowired
    public AllEventService(RegistrationEvent registrationEvent, GetterEvent getterEvent, CloseEvent closeEvent, EntityBinder entityBinder, GetterUser getterUser) {
        this.registrationEvent = registrationEvent;
        this.getterEvent = getterEvent;
        this.closeEvent = closeEvent;
        this.entityBinder = entityBinder;
        this.getterUser = getterUser;
    }

    public void createEvent(EventDTO eventDTO) {
        registrationEvent.createEvent(eventDTO);
    }

    public String closeEvent(UUID id) {
        return closeEvent.closeEvent(id);
    }

    public Set<Event> getEvents(RequestEventDTO requestEventDTO) { //TODO: added filter Passed/Active
        OptionsDTO options = requestEventDTO.getOptions();
        if (options.isAllEvents()) {
            return getterEvent.getEvents();
        } else {
            User user = getterUser.getUser(requestEventDTO.getUserId());
            return user.getEvents();
        }
    }

    public Event getEvent(UUID id) {
        return getterEvent.getEvent(id);
    }

    public void addUser(UUID idEvent, UUID idUser) {
       entityBinder.addUserToEvent(idEvent, idUser);
    }

    public String deleteUser(UUID idEvent, UUID idUser) {
        return entityBinder.deleteUserFromEvent(idEvent, idUser);
    }
}
