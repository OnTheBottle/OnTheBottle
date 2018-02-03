package com.bottle.event.service;

import com.bottle.event.model.DAO.EntityDAO;
import com.bottle.event.model.DAO.EntityDAOImpl;
import com.bottle.event.model.entity.Event;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class EventRegistrationService {
    private EventRegistrationService() {
    }

    private static EventRegistrationService instance = null;

    public static EventRegistrationService getEventRegistrationService() {
        if (instance == null) {
            instance = new EventRegistrationService();
        }
        return instance;
    }

    public String registrationEvent(Map<String, String[]> paramMap) {
        String text = paramMap.get("text")[0];
        int idPlace = Integer.parseInt(paramMap.get("idPlace")[0]);
        Date startTime = new Date();
        Date endTime = new Date();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
            startTime = dateFormat.parse(paramMap.get("start_time")[0]);
            endTime = dateFormat.parse(paramMap.get("end_time")[0]);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Event event = new Event();
        event.setText(text);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setIdPlace(idPlace);

        String resultData;
        EntityDAO<Event> eventEntityDAO = new EntityDAOImpl<>();
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
