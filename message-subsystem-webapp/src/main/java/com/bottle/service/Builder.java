package com.bottle.service;

import com.bottle.model.DTO.Request.EventDTO;
import com.bottle.model.entity.Event;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class Builder {
    public Event buildEvent(EventDTO eventDTO) {
        Event event = new Event();
        String title = eventDTO.getTitle();
        String text = eventDTO.getText();
        Date startTime = Utilities.formatDate(eventDTO.getStartTime());
        Date endTime = Utilities.formatDate(eventDTO.getEndTime());

        event.setTitle(title);
        event.setText(text);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setUsersCounter(0);
        event.setIsActive(true);
        event.setId(UUID.randomUUID());

        return event;
    }
}
