package com.bottle.event.service.user;

import com.bottle.event.model.entity.User;
import com.bottle.event.model.repository.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class RegistrationUser {
    private UserStore userStore;

    @Autowired
    public RegistrationUser(UserStore userStore) {
        this.userStore = userStore;
    }

    public String create() {
        try {
            UUID uuid = UUID.randomUUID();
            User user = new User();
            user.setId(uuid);
            userStore.createOrUpdate(user);
            return "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
