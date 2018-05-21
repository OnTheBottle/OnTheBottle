package com.bottle.service.event;

import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import com.bottle.model.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class GetterEvent {
    private EventRepository eventRepository;

    @Autowired
    public GetterEvent(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event getEvent(UUID id) throws NotEventException {
        if (!eventRepository.exists(id)) throw new NotEventException("No events with id: " + id);
        return eventRepository.getOne(id);
    }

    public Set<Event> getActiveEvents() {
        return eventRepository.findAllByIsActive(true);
    }

    public Set<Event> getPassedEvents() {
        return eventRepository.findAllByIsActive(false);
    }

    public Set<Event> getActiveFromUser(UUID idUser) {
        return eventRepository.getEventsFromUserIsActive(idUser, true);
    }

    public Set<Event> getPassedFromUser(UUID idUser) {
        return eventRepository.getEventsFromUserIsActive(idUser, false);
    }

    public Set<Event> getOwnerFromUser(User user) {
        return eventRepository.getEventsByOwnerAndIsActiveTrue(user);
    }
}
