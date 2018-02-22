package com.bottle.event.service;

import com.bottle.event.model.DAO.EntityDAOImpl;
import com.bottle.event.model.DAO.EventDAOImpl;
import com.bottle.event.model.entity.Event;
import com.bottle.event.model.entity.Place;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class UpdateEvent {
    private static final RegistrationPlace REGISTRATION_PLACE = new RegistrationPlace();

    public String updateEvent(Map<String, String> paramMap) {
        UUID id = UUID.randomUUID();
        String title = paramMap.get("title");
        String text = paramMap.get("text");
        Date startTime = formatDate(paramMap.get("start_time"));
        Date endTime = formatDate(paramMap.get("end_time"));
        Place place = REGISTRATION_PLACE.registration(UUID.fromString(paramMap.get("idPlace")));

        //TODO

        Event event = null;
        event.setId(id);
        event.setTitle(title);
        event.setText(text);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setPlace(place);

        return "complete";
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
