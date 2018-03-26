package com.bottle.event.model.repository;

import com.bottle.event.model.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
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
    public void createOrUpdate(Event event) throws SQLException {
        eventRepository.save(event);
    }

    @Transactional
    public void delete(UUID id) throws SQLException {
        eventRepository.delete(id);
    }

    @Transactional
    public Event getById(UUID id) throws SQLException {
        return eventRepository.getOne(id);
    }

    @Transactional
    public List<Event> getAllActive() throws SQLException {
        return eventRepository.findAllByIsActive(true);
    }

    @Transactional
    public List<Event> getAllPassed() throws SQLException {
        return eventRepository.findAllByIsActive(false);
    }
}

