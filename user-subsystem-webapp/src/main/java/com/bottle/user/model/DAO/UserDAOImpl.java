package com.bottle.user.model.DAO;

import com.bottle.user.model.hibernate.HibernateSessionFactory;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.Set;
import com.bottle.user.model.entity.UserEntity;

public class UserDAOImpl implements UserDAO {

    public Object getEntityByID(long id){
        Session session = null;
        UserEntity user = null;
        try{
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            user = session.load(UserEntity.class, id);

            //         System.out.println("A name of user is " + user.getName());
            session.getTransaction().commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally{
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return user;
    }

    public Set getAllEntities() throws SQLException {
        return null;
    }
}
