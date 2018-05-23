package com.bottle.service.event;

import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import com.bottle.model.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetterEvent {
    private static final int eventsCount = 3;
    private EventRepository eventRepository;

    @Autowired
    public GetterEvent(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event getEvent(UUID id) throws NotEventException {
        if (!eventRepository.exists(id)) throw new NotEventException("No events with id: " + id);
        return eventRepository.getOne(id);
    }

    public List<Event> getActiveEvents(int eventsPage, String sortType) {
        return eventRepository.findAllByIsActive(true, getPageRequest(eventsPage, sortType));
    }

    public List<Event> getPassedEvents(int eventsPage, String sortType) {
        return eventRepository.findAllByIsActive(false, getPageRequest(eventsPage, sortType));
    }

    public List<Event> getActiveFromUser(UUID idUser, int eventsPage, String sortType) {
        return eventRepository.getEventsFromUserIsActive(idUser, true, getPageRequest(eventsPage, sortType));
    }

    public List<Event> getPassedFromUser(UUID idUser, int eventsPage, String sortType) {
        return eventRepository.getEventsFromUserIsActive(idUser, false, getPageRequest(eventsPage, sortType));
    }

    public List<Event> getOwnerFromUser(User user, int eventsPage, String sortType) {
        return eventRepository.getEventsByOwnerAndIsActiveTrue(user, getPageRequest(eventsPage, sortType));
    }

    private PageRequest getPageRequest(int eventsPage, String sortType) {
        switch (sortType) {
            case "title": return new  PageRequest(eventsPage, eventsCount, Sort.Direction.ASC, sortType);
            case "startTime": return new  PageRequest(eventsPage, eventsCount, Sort.Direction.DESC, sortType);
            case "text": return new  PageRequest(eventsPage, eventsCount, Sort.Direction.ASC, sortType);
            case "usersCounter": return new  PageRequest(eventsPage, eventsCount, Sort.Direction.DESC, sortType);
            default: return new  PageRequest(eventsPage, eventsCount, Sort.Direction.DESC, "startTime");
        }
    }
}
