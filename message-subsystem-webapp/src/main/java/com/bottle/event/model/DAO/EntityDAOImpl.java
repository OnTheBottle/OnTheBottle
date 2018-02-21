package com.bottle.event.model.DAO;

import com.bottle.event.model.hibernate.HibernateSessionFactory;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.Set;

public abstract class EntityDAOImpl<T> {
    protected final static Session SESSION = HibernateSessionFactory.getSessionFactory().openSession();

    public void addEntity(T object) throws SQLException {
        SESSION.beginTransaction();
        SESSION.save(object);
        SESSION.getTransaction().commit();
    }

    public void updateEntity(T object) throws SQLException {
        SESSION.beginTransaction();
        SESSION.update(object);
        SESSION.getTransaction().commit();
    }

    public void deleteEntity(T object) throws SQLException {
        SESSION.beginTransaction();
        SESSION.delete(object);
        SESSION.getTransaction().commit();
    }

    public abstract T getEntityByID(long id) throws SQLException;

    public abstract Set<T> getAllEntities() throws SQLException;
}
