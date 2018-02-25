package com.bottle.event.service.event;

import com.bottle.event.model.entity.Event;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class BuildEvent {
    public Event build(Map<String, String> paramMap) {
        UUID id = UUID.randomUUID();
        String title = paramMap.get("title");
        String text = paramMap.get("text");
        Date startTime = formatDate(paramMap.get("start_time"));
        Date endTime = formatDate(paramMap.get("end_time"));

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
            date = dateFormat.parse(param);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
