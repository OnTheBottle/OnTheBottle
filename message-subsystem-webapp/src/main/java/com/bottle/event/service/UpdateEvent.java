package com.bottle.event.service;

import com.bottle.event.model.DAO.EntityDAOImpl;
import com.bottle.event.model.DAO.EventDAOImpl;
import com.bottle.event.model.entity.Event;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class UpdateEvent {
    public String updateEvent(Map<String, String> paramMap) {
        long id = Long.parseLong(paramMap.get("id"));
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

        String resultData;
        EntityDAOImpl<Event> eventEntityDAO = new EventDAOImpl();
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
