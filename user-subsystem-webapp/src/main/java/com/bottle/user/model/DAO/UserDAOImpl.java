package com.bottle.user.model.DAO;

import com.bottle.user.model.hibernate.HibernateSessionFactory;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.Set;
import com.bottle.user.model.entity.UserEntity;

public class UserDAOImpl implements UserDAO {
    @Override
    public Object getEntityByID(long id) throws SQLException {
        Session session = null;
        UserEntity user = null;
        try{
            session = HibernateSessionFactory.getSessionFactory().openSession();
            user = session.load(UserEntity.class, id);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally{
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return user;
    }

    @Override
    public Set getAllEntities() throws SQLException {
        return null;
    }
}
