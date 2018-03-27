package com.bottle.event.service.event;

import com.bottle.event.model.entity.Event;
import com.bottle.event.model.repository.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class GetterEvent {
    private EventStore eventStore;

    @Autowired
    public GetterEvent(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public Map<UUID, String> getAllActiveIdAndTitle() {
        try {
            Map<UUID, String> events = new HashMap<>();
            List<Event> eventList = eventStore.getAllActive();
            Date dateNow = new Date();

            for (Event event : eventList) {
                if (event.getEndTime().after(dateNow)) {
                    events.put(event.getId(), event.getTitle());
                } else {
                    event.setIsActive(false);
                    eventStore.createOrUpdate(event);
                }
            }

            return events;
        } catch (SQLException e) { // TODO
            e.printStackTrace();
            return null;
        }
    }

    public Map<UUID, String> getAllPassedIdAndTitle() {
        try {
            Map<UUID, String> events = new HashMap<>();
            List<Event> eventList = eventStore.getAllPassed();

            for (Event event : eventList) {
                events.put(event.getId(), event.getTitle());
            }

            return events;
        } catch (SQLException e) { // TODO
            e.printStackTrace();
            return null;
        }
    }

    public Event getEvent(UUID id) {
        try {
            return eventStore.getById(id);
        } catch (SQLException e) { //TODO
            e.printStackTrace();
            return null;
        }
    }
}
