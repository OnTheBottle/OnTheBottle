package com.bottle.service.event;

import com.bottle.model.entity.Event;
import com.bottle.model.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class GetterEvent {
    private EventRepository eventRepository;

    @Autowired
    public GetterEvent(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event getEvent(UUID id) {
        return eventRepository.getOne(id);
    }

    public Set<Event> getEvents() {
        return new HashSet<>(eventRepository.findAll());
    }


}
