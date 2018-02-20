package com.bottle.userWall.dao;

import org.hibernate.*;
import org.hibernate.criterion.Order;
import com.bottle.userWall.utils.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.showMessageDialog;


public class PostsDAOImpl implements PostDAO {
    public void addPost(Posts post) throws SQLException {

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save( post );
            session.getTransaction().commit();
        } catch (Exception e) {
            showMessageDialog( null, e.getMessage(), "Ошибка при добавлении", OK_OPTION );
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public Posts getPostById(int id) throws SQLException {
        Session session = null;
        Posts post = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            post = session.load( Posts.class, id );
        } catch (Exception e) {
            showMessageDialog( null, e.getMessage(), "Ошибка 'findById'", OK_OPTION );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return post;
    }

    public List<Posts> getAllPosts() throws SQLException {
        Session session = null;
        List<Posts> posts = new ArrayList<Posts>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            posts = session.createCriteria( Posts.class ).list();
        } catch (Exception e) {
            showMessageDialog( null, e.getMessage(), "K Hyam", OK_OPTION );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return posts;
    }

    public void deletePost(Posts post) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete( post );
            session.getTransaction().commit();
        } catch (Exception e) {
            showMessageDialog( null, e.getMessage(), "Ошибка при удалении", OK_OPTION );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void deleteAll() {
        Session session = null;
        List<Posts> posts = new ArrayList<Posts>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            posts = session.createCriteria( Posts.class ).list();
            session.beginTransaction();
            for (Posts p : posts)
                session.delete( p );
            session.getTransaction().commit();
        } catch (Exception e) {
            showMessageDialog( null, e.getMessage(), "Ошибка удаления", OK_OPTION );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Posts> getPosts() throws SQLException {
        Session session = null;
        List<Posts> posts = new ArrayList<Posts>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria=session.createCriteria( Posts.class );
            criteria.addOrder( Order.desc( "date" ) );
            criteria.setMaxResults( 3 );
            posts=criteria.list();
        } catch (Exception e) {
            showMessageDialog( null, e.getMessage(), "Ошибка выборки", OK_OPTION );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return posts;
    }
}