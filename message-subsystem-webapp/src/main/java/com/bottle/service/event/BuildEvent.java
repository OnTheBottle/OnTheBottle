package com.bottle.service.event;

import com.bottle.model.DTO.EventDTO;
import com.bottle.model.entity.Event;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
public class BuildEvent {
    public Event build(EventDTO eventDTO) {
        String title = eventDTO.getTitle();
        String text = eventDTO.getText();
        Date startTime = formatDate(eventDTO.getStartTime());
        Date endTime = formatDate(eventDTO.getEndTime());

        Event event = new Event();
        event.setTitle(title);
        event.setText(text);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setIsActive(true);

        return event;
    }

    private Date formatDate(String param) {
        Date date = new Date();
        try {
            param = param.replace('T', ' ');
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC+2"));
            date = dateFormat.parse(param);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
