package com.bottle.event.model.DAO;

import com.bottle.event.model.hibernate.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.Set;

public class EntityDAOImpl<T> implements EntityDAO<T> {
    private final static Session SESSION = HibernateSessionFactory.getSessionFactory().openSession();

    public void addEntity(T object) {
        SESSION.beginTransaction();
        SESSION.save(object);
        SESSION.getTransaction().commit();
    }

    public void updateEntity(T object) {

    }

    public void deleteEntity(T object) {

    }

    public T getEntityByID(long id) {
        return null;
    }

    public Set<T> getAllEntities() {
        return null;
    }
}
