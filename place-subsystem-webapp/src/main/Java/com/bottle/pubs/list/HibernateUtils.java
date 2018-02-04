package com.bottle.pubs.list;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory = null;
    static {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;

    }
}
