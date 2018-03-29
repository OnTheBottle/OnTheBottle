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

            for (User place : users) {
                uuids.add(place.getId());
            }

            return uuids;
        } catch (SQLException e) { // TODO
            e.printStackTrace();
            return null;
        }
    }

    public User createOrGet(UUID id) {
        try {
            if (userStore.exists(id)) {
                return userStore.getById(id);
            } else {
                User place = new User();
                place.setId(id);
                userStore.createOrUpdate(place);
                return place;
            }
        } catch (SQLException e) { //TODO
            e.printStackTrace();
            return null;
        }
    }
}
