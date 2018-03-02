package com.bottle.event.service.event;

import com.bottle.event.model.DTO.EventDTO;
import com.bottle.event.model.entity.Event;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

@Service
public class BuildEvent {
    private boolean active;

    public Event build(EventDTO eventDTO) { //TODO
        UUID id = eventDTO.getId().equals("0") ?
                UUID.randomUUID() : UUID.fromString(eventDTO.getId());

        String title = eventDTO.getTitle();
        String text = eventDTO.getText();
        Date startTime = formatDate(eventDTO.getStartTime());
        Date endTime = formatDate(eventDTO.getEndTime());


        Event event = new Event();
        event.setId(id);
        event.setTitle(title);
        event.setText(text);
        event.setStartTime(startTime);
        event.setEndTime(endTime);

        return event;
    }

    private Date formatDate(String param) {
        Date date = new Date();
        try {
            param = param.replace('T', ' ');
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dateFormat.setTimeZone(TimeZone.getDefault());
            date = dateFormat.parse(param);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
