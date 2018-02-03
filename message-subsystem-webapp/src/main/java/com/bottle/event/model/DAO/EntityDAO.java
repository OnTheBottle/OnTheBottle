package com.bottle.event.model.DAO;

import java.sql.SQLException;
import java.util.Set;

public interface EntityDAO<T> {
    void addEntity(T object) throws SQLException;

    void updateEntity(T object) throws SQLException;

    void deleteEntity(T object) throws SQLException;

    T getEntityByID(long id) throws SQLException;

    Set<T> getAllEntities() throws SQLException;
}
