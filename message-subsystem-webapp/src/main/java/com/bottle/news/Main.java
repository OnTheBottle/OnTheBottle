package com.bottle.news;

import com.bottle.news.dao.FactoryDAO;
import com.bottle.news.dao.HibernateFactory;
import com.bottle.news.dao.entity.Post;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        FirstStart.initialization();

        //test getAllPosts()
        List<Post> posts = FactoryDAO.getFactory().getPostDAO().getAllPosts(5);
        for (Post p : posts) {
            System.out.println(p);
        }

        //test getPostById()
        String postId = "cf227275-ef2b-4273-a0e5-4af707276372";
        Post post = FactoryDAO.getFactory().getPostDAO().getPostById(postId);
        System.out.println(post);

        //test getPostsByUserId()
        String userId = "8c339571-5b51-48d1-a70e-840071ab778c";
        List<Post> postList = FactoryDAO.getFactory().getPostDAO().getPostsByUser(userId, 10);
        for (Post p : postList) {
            System.out.println(p);
        }

        List<Post> friendsPosts = FactoryDAO.getFactory().getPostDAO().getPostsOfFriends(FirstStart.getFriends(), 20);
        for (Post p : friendsPosts) {
            System.out.println(p);
        }

        HibernateFactory.getSessionFactory().close();
    }
}
