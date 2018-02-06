package com.bottle.news;

import com.bottle.news.dao.HibernateFactory;
import com.bottle.news.dao.entity.Post;
import com.bottle.news.dao.entity.Security;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        System.out.println("BUGAGAGAGGAGA!!!!!");
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {

            Transaction transaction;

            transaction = session.beginTransaction();

            System.out.println(transaction.getStatus());

            Security security = new Security();
            security.setName("Public");
            session.save(security);
            session.flush();
            security.setName("Group");
            session.save(security);
            session.flush();
            security.setName("Private");
            session.save(security);
            transaction.commit();

            transaction.begin();
            Post post = new Post();
            post.setAuthorId("43657843654365L");
            post.setDate(new Date());
            post.setPost("Hello World");
            session.save(post);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        HibernateFactory.getSessionFactory().close();

    }
}
