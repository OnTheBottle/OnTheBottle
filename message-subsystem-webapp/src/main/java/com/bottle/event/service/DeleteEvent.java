package com.bottle.event.service;

import com.bottle.event.model.DAO.EntityDAOImpl;
import com.bottle.event.model.DAO.EventDAOImpl;
import com.bottle.event.model.entity.Event;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class DeleteEvent {
    public String deleteEvent(Map<String, String> paramMap) {
        UUID id = UUID.fromString(paramMap.get("id"));

        //TODO

        return "complete";
    }
}
