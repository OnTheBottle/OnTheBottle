package com.bottle.news.dao;

import com.bottle.news.FirstStart;
import com.bottle.news.dao.entity.User;

import java.util.HashSet;
import java.util.Set;

public class UserDAOImpl {

    public Set<User> getFriends(String userId) {
        Set<User> friends;

        friends = FirstStart.getFriends(); //add test friends
        return friends;
    }
}
