package com.bottle.news;

import com.bottle.news.dao.FactoryDAO;
import com.bottle.news.dao.HibernateFactory;
import com.bottle.news.dao.entity.Post;
import com.bottle.news.dao.entity.Security;
import com.bottle.news.dao.entity.User;
import javafx.geometry.Pos;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        FirstStart.initialization();

        //test getPosts()
        List<Post> posts = FactoryDAO.getFactory().getPostDAO().getPosts(5);
        for (Post p: posts){
            System.out.println(p);
        }

        //test getPostById()
        String postId = "cf227275-ef2b-4273-a0e5-4af707276372";
        Post post = FactoryDAO.getFactory().getPostDAO().getPostById(postId);
        System.out.println(post);

        //test getPostsByUserId()
        String userId = "8c339571-5b51-48d1-a70e-840071ab778c";
        List<Post> postList = FactoryDAO.getFactory().getPostDAO().getPostsByUserId(userId);
        for (Post p: postList){
            System.out.println(p);
        }

        HibernateFactory.getSessionFactory().close();
    }
}
