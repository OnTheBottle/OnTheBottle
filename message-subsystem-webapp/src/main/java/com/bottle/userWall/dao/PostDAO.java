package com.bottle.userWall.dao;

import java.sql.SQLException;
import java.util.List;


public interface PostDAO {
    public void addPost(Posts post) throws SQLException;
    public Posts getPostById(int id) throws SQLException;
    public void deletePost(Posts post) throws SQLException;
    public List<Posts> getAllPosts()throws SQLException;
    public void deleteAll() throws SQLException;
    public List<Posts> getPosts()throws SQLException;
    }
