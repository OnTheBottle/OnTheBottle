package com.bottle.event.service;

import com.bottle.event.model.DAO.EntityDAOImpl;
import com.bottle.event.model.DAO.EventDAOImpl;
import com.bottle.event.model.entity.Event;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class RegistrationEvent {
    public String registrationEvent(Map<String, String> paramMap) {
        String text = paramMap.get("text");
        int idPlace = Integer.parseInt(paramMap.get("idPlace"));
        Date startTime = new Date();
        Date endTime = new Date();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
            startTime = dateFormat.parse(paramMap.get("start_time"));
            endTime = dateFormat.parse(paramMap.get("end_time"));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Event event = new Event();
        event.setText(text);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setIdPlace(idPlace);

        String resultData;
        EntityDAOImpl<Event> eventEntityDAO = new EventDAOImpl();
        try {
            eventEntityDAO.addEntity(event);
            resultData = "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            resultData = "error";
        }
        return resultData;
    }
}
