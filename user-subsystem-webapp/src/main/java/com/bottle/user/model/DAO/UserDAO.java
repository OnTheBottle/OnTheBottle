package com.bottle.user.model.DAO;

import java.sql.SQLException;
import java.util.Set;

public interface UserDAO <T>{
    T getEntityByID(long id) throws SQLException;

    Set<T> getAllEntities() throws SQLException;
}
