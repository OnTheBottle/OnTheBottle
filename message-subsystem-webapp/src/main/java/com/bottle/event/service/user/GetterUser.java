package com.bottle.event.service.user;

import com.bottle.event.model.entity.User;
import com.bottle.event.model.repository.UserStore;
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
}
