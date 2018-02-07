package com.bottle.news.dao;

import com.bottle.news.dao.entity.Post;
import com.bottle.news.dao.entity.User;
import org.hibernate.Session;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class PostDAOImpl {

    public Post getPostById(String id) {
        Post post = new Post();
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            post = session.get(Post.class, id);
            System.out.println(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

/*
    public List<Post> getPostsOfFriends(Set<User> friends) {
        List<Post> postList = new ArrayList<>();
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            // Query query = session.createQuery("from Post where");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
}
