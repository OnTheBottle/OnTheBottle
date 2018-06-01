package com.bottle.service.event;

import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;

@Service
public class EntityBinder {
    public boolean addUserToEvent(Event event, User user) {
        if (event.getIsActive()) {
            event.getUsers().add(user);
            user.getEvents().add(event);
            event.setUsersCounter(event.getUsersCounter() + 1);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUserFromEvent(Event event, User user) {
        if (event.getUsers().size() != 0 && event.getIsActive()) {
            event.getUsers().remove(user);
            user.getEvents().remove(event);
            event.setUsersCounter(event.getUsersCounter() - 1);
            if (user.equals(event.getOwner())) {
                User owner = getRandomOwner(event.getUsers());
                if (owner != null) {
                    event.setOwner(owner);
                } else {
                    event.setOwner(null);
                    event.setIsActive(false);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private User getRandomOwner(Set<User> users) {
        if (users.size() == 0) return null;
        else {
            User owner = null;
            int item = new Random().nextInt(users.size());
            int i = 0;
            for (User user : users) {
                if (i == item) {
                    owner = user;
                    break;
                }
                i++;
            }
            return owner;
        }
    }
}
