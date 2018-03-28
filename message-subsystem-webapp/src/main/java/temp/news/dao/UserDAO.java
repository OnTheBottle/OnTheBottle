package temp.news.dao;

import temp.news.FirstStart;
import temp.news.dao.entity.User;

import java.util.Set;

public class UserDAO {

    public Set<User> getFriends(String userId) {
        Set<User> friends;

        friends = FirstStart.getFriends(); //add test friends
        return friends;
    }
}
