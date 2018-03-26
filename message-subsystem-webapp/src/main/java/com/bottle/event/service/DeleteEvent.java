package com.bottle.event.service;

import com.bottle.event.model.DAO.EntityDAOImpl;
import com.bottle.event.model.DAO.EventDAOImpl;
import com.bottle.event.model.entity.Event;

import java.sql.SQLException;
import java.util.Map;

public class DeleteEvent {
    public String deleteEvent(Map<String, String> paramMap) {
        long id = Long.parseLong(paramMap.get("id"));

        String resultData;
        EntityDAOImpl<Event> eventEntityDAO = new EventDAOImpl();
        try {
            Event event = eventEntityDAO.getEntityByID(id);
            eventEntityDAO.deleteEntity(event);
            resultData = "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            resultData = "error";
        }
        return resultData;
    }
}
