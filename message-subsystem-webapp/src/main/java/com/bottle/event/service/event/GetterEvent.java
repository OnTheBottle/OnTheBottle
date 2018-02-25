package com.bottle.event.service.event;

import com.bottle.event.model.entity.Event;
import com.bottle.event.model.repository.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetterEvent {
    private EventStore eventStore;

    @Autowired
    public GetterEvent(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public List<String> getAllEvents() {
        try {
            List<String> idEvents = new ArrayList<>();
            List<Event> events = eventStore.getAll();

            for (Event event : events) {
                idEvents.add(String.valueOf(event.getId()));
            }

            return idEvents;
        } catch (SQLException e) { // TODO
            e.printStackTrace();
            return null;
        }
    }
}
