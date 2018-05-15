package com.bottle.service.event;

import com.bottle.model.DTO.EventDTO;
import com.bottle.model.DTO.EventResponseDTO;
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

    public Set<EventResponseDTO> getEvents(RequestEventDTO requestEventDTO) {
        OptionsDTO options = requestEventDTO.getOptions();
        User user = getterUser.getUser(requestEventDTO.getUserId());
        if (options.isAllEvents()) {
            if (options.isActiveEvents()) {
                if (options.isOwnerEvents()) {
                    return setResponseInfo(getterEvent.getOwnerFromUser(user), user);
                }
                return setResponseInfo(checkPassedEvents(getterEvent.getActiveEvents()), user);
            }
            return setResponseInfo(getterEvent.getPassedEvents(), user);
        }

        if (options.isActiveEvents()) {
            if (options.isOwnerEvents()) {
                return setResponseInfo(getterEvent.getOwnerFromUser(user), user);
            }
            return setResponseInfo(checkPassedEvents(getterEvent.getActiveFromUser(requestEventDTO.getUserId())), user);
        }
        return setResponseInfo(getterEvent.getPassedFromUser(requestEventDTO.getUserId()), user);
    }

    public void updateEvent(EventDTO eventDTO) {
        registrationEvent.updateEvent(eventDTO);
    }

    public void closeEvent(UUID id) {
        closeEvent.closeEvent(id);
    }

    public Event getEvent(UUID id) {
        return getterEvent.getEvent(id);
    }

    public String addUser(UUID idEvent, UUID idUser) {
        return entityBinder.addUserToEvent(idEvent, idUser);
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

    private Set<EventResponseDTO> setResponseInfo(Set<Event> events, User user) {
        Set<EventResponseDTO> eventsInfo = new HashSet<>();

        for (Event event : events) {
            EventResponseDTO eventResponseDTO = new EventResponseDTO();
            eventResponseDTO.setId(event.getId());
            eventResponseDTO.setTitle(event.getTitle());
            eventResponseDTO.setText(event.getText());
            eventResponseDTO.setStartTime(event.getStartTime());
            eventResponseDTO.setEndTime(event.getEndTime());
            eventResponseDTO.setPlace(event.getPlace());
            eventResponseDTO.setMember(event.getUsers().contains(user));
            eventResponseDTO.setUsersCounter(event.getUsers().size()); //TODO
            eventResponseDTO.setActive(event.getIsActive());
            eventResponseDTO.setOwner(event.getOwner() != null && event.getOwner().equals(user));
            eventsInfo.add(eventResponseDTO);
        }

        return eventsInfo;
    }
}