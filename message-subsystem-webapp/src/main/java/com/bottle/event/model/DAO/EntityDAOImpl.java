package com.bottle.event.model.DAO;

import com.bottle.event.model.hibernate.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.Set;

public abstract class EntityDAOImpl<T> implements EntityDAO<T> {
    protected final static Session SESSION = HibernateSessionFactory.getSessionFactory().openSession();

    public void addEntity(T object) {
        SESSION.beginTransaction();
        SESSION.save(object);
        SESSION.getTransaction().commit();
    }

    public void updateEntity(T object) {
        SESSION.beginTransaction();
        SESSION.update(object);
        SESSION.getTransaction().commit();
    }

    public void deleteEntity(T object) {

    }

    public abstract T getEntityByID(long id);

    public abstract Set<T> getAllEntities();
}
