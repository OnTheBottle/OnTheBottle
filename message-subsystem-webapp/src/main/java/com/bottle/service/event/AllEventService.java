package com.bottle.service.event;

import com.bottle.model.DTO.EventDTO;
import com.bottle.model.DTO.OptionsDTO;
import com.bottle.model.DTO.RequestEventDTO;
import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import com.bottle.model.repository.EventRepository;
import com.bottle.service.user.GetterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AllEventService {
    private RegistrationEvent registrationEvent;
    private GetterEvent getterEvent;
    private CloseEvent closeEvent;
    private EntityBinder entityBinder;
    private GetterUser getterUser;
    private EventRepository eventRepository;

    @Autowired
    public AllEventService(RegistrationEvent registrationEvent, GetterEvent getterEvent, CloseEvent closeEvent, EntityBinder entityBinder, GetterUser getterUser, EventRepository eventRepository) {
        this.registrationEvent = registrationEvent;
        this.getterEvent = getterEvent;
        this.closeEvent = closeEvent;
        this.entityBinder = entityBinder;
        this.getterUser = getterUser;
        this.eventRepository = eventRepository;
    }

    public void createEvent(EventDTO eventDTO) {
        registrationEvent.createEvent(eventDTO);
    }

    public Set<Event> getEvents(RequestEventDTO requestEventDTO) {
        OptionsDTO options = requestEventDTO.getOptions();
        if (options.isAllEvents()) {
            if ((options.isActiveEvents() && options.isPassedEvents()) || (!options.isActiveEvents() && !options.isPassedEvents())) {
                return checkPassedEvents(getterEvent.getEvents());
            } else if (options.isActiveEvents()) {
                return getterEvent.getActiveEvents();
            } else {
                return getterEvent.getPassedEvents();
            }
        } else {
            User user = getterUser.getUser(requestEventDTO.getUserId());
            if ((options.isActiveEvents() && options.isPassedEvents()) || (!options.isActiveEvents() && !options.isPassedEvents())) {
                return checkPassedEvents(new HashSet<>(user.getEvents()));
                //return checkPassedEvents(user.getEvents());
            } else if (options.isActiveEvents()) {
                return getterEvent.getActiveFromUser(requestEventDTO.getUserId());
            } else {
                return getterEvent.getPassedFromUser(requestEventDTO.getUserId());
            }
        }
    }

    public void updateEvent(EventDTO eventDTO) {
        registrationEvent.updateEvent(eventDTO);
    }

    public String closeEvent(UUID id) {
        return closeEvent.closeEvent(id);
    }

    public Event getEvent(UUID id) {
        return getterEvent.getEvent(id);
    }

    public void addUser(UUID idEvent, UUID idUser) {
        entityBinder.addUserToEvent(idEvent, idUser);
    }

    public void deleteUser(UUID idEvent, UUID idUser) {
        entityBinder.deleteUserFromEvent(idEvent, idUser);
    }

    private Set<Event> checkPassedEvents(Set<Event> events) {
        Date today = new Date();
        for (Event event : events) {
            if (event.getEndTime().before(today)) {
                event.setIsActive(false);
                eventRepository.save(event);
            }
        }
        return events;
    }
}