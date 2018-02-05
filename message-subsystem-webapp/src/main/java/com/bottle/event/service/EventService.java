package com.bottle.event.service;

import com.bottle.event.model.DAO.EntityDAO;
import com.bottle.event.model.DAO.EntityDAOImpl;
import com.bottle.event.model.DAO.EventDAOImpl;
import com.bottle.event.model.entity.Event;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class EventService {
    private EventService() {
    }

    private static EventService instance = null;

    public static EventService getEventService() {
        if (instance == null) {
            instance = new EventService();
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
        EntityDAO<Event> eventEntityDAO = new EventDAOImpl();
        try {
            eventEntityDAO.addEntity(event);
            resultData = "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            resultData = "error";
        }
        return resultData;
    }

    public String updateEvent(Map<String, String[]> paramMap) {
        long id = Long.parseLong(paramMap.get("id")[0]);
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

        String resultData;
        EntityDAO<Event> eventEntityDAO = new EventDAOImpl();
        try {
            Event event = eventEntityDAO.getEntityByID(id);
            event.setText(text);
            event.setStartTime(startTime);
            event.setEndTime(endTime);
            event.setIdPlace(idPlace);
            eventEntityDAO.updateEntity(event);
            resultData = "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            resultData = "error";
        }
        return resultData;
    }
}
