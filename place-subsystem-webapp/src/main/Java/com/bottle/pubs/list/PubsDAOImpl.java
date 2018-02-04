package com.bottle.pubs.list;

import org.hibernate.Session;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PubsDAOImpl implements PubsDAO {
    @Override
    public List getAllPubs() throws SQLException {
        Session session = null;
        List<Pub> pubList = new ArrayList<Pub>();
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            pubList = session.createCriteria(Pub.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Oшибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }

        }
        return pubList;
    }
}
