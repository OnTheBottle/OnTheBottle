package com.bottle.service.user;

import com.bottle.model.entity.User;
import com.bottle.model.repository.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GetterUser {
    private UserStore userStore;

    @Autowired
    public GetterUser(UserStore userStore) {
        this.userStore = userStore;
    }

    public List<UUID> getAllId() {
        try {
            List<UUID> uuids = new ArrayList<>();
            List<User> users = userStore.getAll();

            for (User user : users) {
                uuids.add(user.getId());
            }

            return uuids;
        } catch (SQLException e) { // TODO
            e.printStackTrace();
            return null;
        }
    }

    public User getUser(UUID id) {
        try {
            if (userStore.exists(id)) {
                return userStore.getById(id);
            } else {
                return createUser(id);
            }
        } catch (SQLException e) { //TODO
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAll() {
        try {
            return userStore.getAll();
        } catch (SQLException e) { //TODO
            e.printStackTrace();
            return null;
        }
    }

    public User createUser(UUID id) {
        try {
            if (userStore.exists(id)) {
                return userStore.getById(id);
            } else {
                User user = new User();
                user.setId(id);
                userStore.createOrUpdate(user);
                return user;
            }
        } catch (SQLException e) { //TODO
            e.printStackTrace();
            return null;
        }
    }
}
