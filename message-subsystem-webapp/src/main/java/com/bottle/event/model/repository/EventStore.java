package com.bottle.event.model.repository;

import com.bottle.event.model.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EventStore {
    private EventRepository eventRepository;

    @Autowired
    public EventStore(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public void createOrUpdate(Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public void delete(UUID id) {
        eventRepository.delete(id);
    }

    @Transactional
    public Event getById(UUID id) {
        return eventRepository.getOne(id);
    }

    @Transactional
    public List<Event> getAll() {
        return eventRepository.findAll();
    }
}
