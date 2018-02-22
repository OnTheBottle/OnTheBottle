package com.bottle.event.service;

import com.bottle.event.model.entity.Event;
import com.bottle.event.model.entity.Place;
import com.bottle.event.model.repository.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class RegistrationEvent {
    private BuildEvent buildEvent;
    private UtilLink utilLink;
    private EventStore eventStore;

    @Autowired
    public RegistrationEvent(BuildEvent buildEvent, UtilLink utilLink, EventStore eventStore) {
        this.buildEvent = buildEvent;
        this.utilLink = utilLink;
        this.eventStore = eventStore;
    }

    public String buildAndSave(Map<String, String> paramMap) {
        Event event = buildEvent.build(paramMap);

        String result;

        try {
            eventStore.createOrUpdate(event);
            utilLink.linkEventAndPlace(event);
            result = "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            result = "error";
        }

        return result;
    }
}
