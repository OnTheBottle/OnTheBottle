package com.bottle.service.event;

import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import com.bottle.model.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

    public Set<Event> getActiveEvents(int eventsPage) {
        return new HashSet<>(eventRepository.findAllByIsActive(true,
                new PageRequest(eventsPage, 3, Sort.Direction.DESC,"startTime")));
    }

    public Set<Event> getPassedEvents(int eventsPage) {
        return new HashSet<>(eventRepository.findAllByIsActive(false,
                new PageRequest(eventsPage, 3, Sort.Direction.DESC, "startTime")));
    }

    public Set<Event> getActiveFromUser(UUID idUser, int eventsPage) {
        return new HashSet<>(eventRepository.getEventsFromUserIsActive(idUser, true));
    }

    public Set<Event> getPassedFromUser(UUID idUser, int eventsPage) {
        return new HashSet<>(eventRepository.getEventsFromUserIsActive(idUser, false));
    }

    public Set<Event> getOwnerFromUser(User user, int eventsPage) {
        return new HashSet<>(eventRepository.getEventsByOwnerAndIsActiveTrue(user,
                new PageRequest(eventsPage, 3, Sort.Direction.DESC, "startTime")));
    }
}
