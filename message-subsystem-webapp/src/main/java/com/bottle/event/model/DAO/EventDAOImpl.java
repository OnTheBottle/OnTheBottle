package com.bottle.event.model.DAO;

import com.bottle.event.model.entity.Event;
import com.bottle.event.model.hibernate.HibernateSessionFactory;
import org.hibernate.Session;

import javax.swing.text.html.parser.Entity;
import java.util.Set;

public class EventDAOImpl extends EntityDAOImpl<Event> {
    public Event getEntityByID(long id) {
        return SESSION.get(Event.class, id);
    }

    public Set<Event> getAllEntities() {
        return null;
    }
}
