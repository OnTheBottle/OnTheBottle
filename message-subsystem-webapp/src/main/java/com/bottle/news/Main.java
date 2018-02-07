package com.bottle.news;

import com.bottle.news.dao.HibernateFactory;
import com.bottle.news.dao.entity.Post;
import com.bottle.news.dao.entity.Security;
import com.bottle.news.dao.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        System.out.println("BUGAGAGAGGAGA!!!!!");
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {

            FirstStart.initialization(session);

/*
            Transaction transaction;
            transaction = session.beginTransaction();
            Post post = new Post();
            post.setAuthorId("43657843654365L");
            post.setDate(new Date());
            post.setPost("Hello World");
            session.save(post);
            //post.setSecurityId(security1);
            session.getTransaction().commit();
            System.out.println(post);

            String ya = String.valueOf(UUID.randomUUID());
            System.out.println(ya);
*/


        } catch (Exception e) {
            e.printStackTrace();
        }

        HibernateFactory.getSessionFactory().close();

    }
}
