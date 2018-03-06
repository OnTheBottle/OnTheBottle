package com.bottle.event.service.event;

import com.bottle.event.model.DTO.request.IdEventAndUserRequestDTO;
import com.bottle.event.model.entity.Event;
import com.bottle.event.model.entity.User;
import com.bottle.event.model.repository.EventStore;
import com.bottle.event.model.repository.UserStore;
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

    public String addUserToEvent(IdEventAndUserRequestDTO idEventAndUserRequestDTO) {
        try {
            Event event = eventStore.getById(UUID.fromString(idEventAndUserRequestDTO.getIdEvent()));
            User user = userStore.getById(UUID.fromString(idEventAndUserRequestDTO.getIdUser()));

            event.getUsers().add(user);
            user.getEvents().add(event);
            eventStore.createOrUpdate(event);

            return "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public void addUserToEvent(User user, Event event) {
        event.getUsers().add(user);
        user.getEvents().add(event);
    }

    public String deleteUserFromEvent(IdEventAndUserRequestDTO idEventAndUserRequestDTO) {
        try {
            Event event = eventStore.getById(UUID.fromString(idEventAndUserRequestDTO.getIdEvent()));
            User user = userStore.getById(UUID.fromString(idEventAndUserRequestDTO.getIdUser()));

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
