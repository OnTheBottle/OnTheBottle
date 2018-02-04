package com.bottle.pubs.list;

public class HibernateFactory {
    private static PubsDAO pubsDAO = null;
    private static HibernateFactory instance = null;

    public static synchronized HibernateFactory getInstance() {
        if (instance == null) {
            instance = new HibernateFactory();
        }
        return instance;
    }

    public PubsDAO getPubsDAO() {
        if (pubsDAO == null) {
            pubsDAO = new PubsDAOImpl();
        }
        return pubsDAO;
    }

}