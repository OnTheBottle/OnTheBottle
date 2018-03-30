package com.bottle.service.event;

import com.bottle.model.DTO.request.IdEventAndUserRequestDTO;
import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import com.bottle.model.repository.EventStore;
import com.bottle.model.repository.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class EntityBinder {
    private EventStore eventStore;
    private UserStore userStore;

    @Autowired
    public EntityBinder(EventStore eventStore, UserStore userStore) {
        this.eventStore = eventStore;
        this.userStore = userStore;
    }

    public String addUserToEvent(UUID idEvent, UUID idUser) {
        try {
            Event event = eventStore.getById(idEvent);
            User user = userStore.getById(idUser);

            event.getUsers().add(user);
            user.getEvents().add(event);
            eventStore.createOrUpdate(event);

            return "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String addUserToEvent(Event event, User user) {
        try {
            event.getUsers().add(user);
            user.getEvents().add(event);
            eventStore.createOrUpdate(event);
            return "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String deleteUserFromEvent(UUID idEvent, UUID idUser) {
        try {
            Event event = eventStore.getById(idEvent);
            User user = userStore.getById(idUser);

            if (user.equals(event.getOwner())) {
                return "Can't delete owner";
            }

            event.getUsers().remove(user);
            user.getEvents().remove(event);
            eventStore.createOrUpdate(event);

            return "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
