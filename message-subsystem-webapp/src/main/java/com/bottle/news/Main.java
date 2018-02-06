package com.bottle.news;

import com.bottle.news.dao.HibernateFactory;
import com.bottle.news.dao.entity.Post;
import org.hibernate.Session;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        System.out.println("BUGAGAGAGGAGA!!!!!");
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Post post = new Post();
            post.setAuthorId(43657843654365L);
            post.setDate(new Date());
            post.setText("Hello World");

            session.beginTransaction();
            session.save(post);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        HibernateFactory.getSessionFactory().close();

    }
}
