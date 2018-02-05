package com.bottle.news.dao;

import com.bottle.news.dao.entity.Author;
import com.bottle.news.dao.entity.Post;

import java.util.List;

public interface PostDAO{
    public List<Post> getPostsById();
    public Author getAuthorById();
}
