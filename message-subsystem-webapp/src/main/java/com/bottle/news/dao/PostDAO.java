package com.bottle.news.dao;

import com.bottle.news.dao.entity.Post;
import com.bottle.news.dao.entity.Security;
import com.bottle.news.dao.entity.User;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.*;

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

    public List<Post> getPostsByUser(String id, int maxRow) {
        List<Post> postList = new ArrayList<>();
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Post where authorId = :authorId order by date").setMaxResults(maxRow);
            query.setParameter("authorId", id);
            postList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postList;
    }

    public List<Post> getPostsByUser(User user, int maxRow) {
        List<Post> postList = new ArrayList<>();
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Post where authorId = :authorId order by date").setMaxResults(maxRow);
            query.setParameter("authorId", user.getId());
            postList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postList;
    }

//    public List<Post> getPostsOfFriendsByUserId(String id, int maxRow) {
//        List<Post> postList = new ArrayList<>();
//
//        Set<User> userSet = FirstStart.getFriends(); // Emulation a Set of Friends
//
//        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
//            Query query = session.createQuery("from Post where authorId = :authorId order by date").setMaxResults(maxRow);
//            query.setParameter("authorId", id);
//            //postList = query.getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return postList;
//    }

    public List<Post> getAllPosts(int maxRow) {
        List<Post> postList = new ArrayList<>();
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Post order by date").setMaxResults(maxRow);
            postList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postList;
    }

    public List<Post> getPostsOfFriends(Set<User> friends, int maxRow) {
        List<Post> postList = new ArrayList<>();
        for (User friend : friends) {
            postList.addAll(getPostsByUser(friend, maxRow));
        }
        Comparator<Post> comparatorMaxToMin = new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                //sort from new date to old date
                return o2.getDate().compareTo(o1.getDate());
            }
        };
        Collections.sort(postList, comparatorMaxToMin);

        int limit = postList.size();
        if (limit > maxRow) limit = maxRow;
        postList = postList.subList(0, limit);

        return postList;
    }
/*
    public List<Post> getPostsOfFriends(Set<User> friends, int maxRow) {
        List<Post> postList = new ArrayList<>();
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            String q = "from Post where authorId in (";
            for (Iterator<User> iterator = friends.iterator(); iterator.hasNext();){
                q += "'" + iterator.next().getId() + "'";
                if (iterator.hasNext()) q += ", ";
            }
            q += ") order by date";
            Query query = session.createQuery(q).setMaxResults(maxRow);
            postList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postList;
    }
*/
}
