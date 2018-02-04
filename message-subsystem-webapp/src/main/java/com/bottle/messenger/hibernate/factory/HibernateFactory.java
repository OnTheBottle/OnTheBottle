package com.bottle.messenger.hibernate.factory;

public class HibernateFactory {

    private static MessengerDao messengerDao = null;
    private static HibernateFactory instance = null;

    public static synchronized HibernateFactory getInstance() {
        if (instance == null) {
            instance = new HibernateFactory();
        }
        return instance;
    }

    public static MessengerDao getMessengerDao() {
        if (messengerDao == null) {
            messengerDao = new MessengerDaoImpl();
        }
        return messengerDao;
    }
}