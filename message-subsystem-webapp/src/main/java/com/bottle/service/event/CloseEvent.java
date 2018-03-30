package com.bottle.service.event;

import com.bottle.model.DTO.request.IdRequestDTO;
import com.bottle.model.entity.Event;
import com.bottle.model.repository.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class CloseEvent {
    private GetterEvent getterEvent;
    private EventStore eventStore;

    @Autowired
    public CloseEvent(GetterEvent getterEvent, EventStore eventStore) {
        this.getterEvent = getterEvent;
        this.eventStore = eventStore;
    }

    public String closeEvent(UUID id) {
        Event event = getterEvent.getEvent(id);
        event.setIsActive(false);

        try {
            eventStore.createOrUpdate(event);
            return "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
