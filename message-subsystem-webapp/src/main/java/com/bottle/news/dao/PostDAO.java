package com.bottle.news.dao;

import com.bottle.news.FirstStart;
import com.bottle.news.dao.entity.Post;
import com.bottle.news.dao.entity.Security;
import com.bottle.news.dao.entity.User;
import javafx.geometry.Pos;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PostDAO {

    public Post getPostById(String id) {
        Post post = new Post();
        Security security = new Security();
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            post = session.get(Post.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public List<Post> getPostsByUserId(String id) {
        List<Post> postList = new ArrayList<>();
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Post where authorId = :authorId order by date");
            query.setParameter("authorId", id);
            postList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postList;
    }

    public List<Post> getPostsOfFriendsByUserId(String id, int maxRow){
        List<Post> postList = new ArrayList<>();

        Set<User> userSet = FirstStart.getFriends(); // Emulation a Set of Friends

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Post where authorId = :authorId order by date").setMaxResults(maxRow);
            query.setParameter("authorId", id);
            postList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postList;
    }

    public List<Post> getPosts(int maxRow){
        List<Post> postList = new ArrayList<>();
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Post order by date").setMaxResults(maxRow);
            postList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postList;
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
