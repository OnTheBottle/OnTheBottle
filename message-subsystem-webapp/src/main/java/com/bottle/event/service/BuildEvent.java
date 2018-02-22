package com.bottle.event.service;

import com.bottle.event.model.entity.Event;
import com.bottle.event.model.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class BuildEvent {
    private RegistrationPlace registrationPlace;

    @Autowired
    public BuildEvent(RegistrationPlace registrationPlace) {
        this.registrationPlace = registrationPlace;
    }

    public Event build(Map<String, String> paramMap) {
        UUID id = UUID.randomUUID();
        String title = paramMap.get("title");
        String text = paramMap.get("text");
        Date startTime = formatDate(paramMap.get("start_time"));
        Date endTime = formatDate(paramMap.get("end_time"));
        Place place = registrationPlace.getPlace(UUID.fromString(paramMap.get("idPlace")));

        Event event = new Event();
        event.setId(id);
        event.setTitle(title);
        event.setText(text);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setPlace(place);

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
