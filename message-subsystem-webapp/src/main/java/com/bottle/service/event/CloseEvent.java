package com.bottle.service.event;

import com.bottle.model.entity.Event;
import com.bottle.model.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CloseEvent {
    private GetterEvent getterEvent;
    private EventRepository eventRepository;

    @Autowired
    public CloseEvent(GetterEvent getterEvent, EventRepository eventRepository) {
        this.getterEvent = getterEvent;
        this.eventRepository = eventRepository;
    }

    public String closeEvent(UUID id) {
        Event event = getterEvent.getEvent(id);
        event.setIsActive(false);

        eventRepository.save(event);
        return "complete";
    }
}
