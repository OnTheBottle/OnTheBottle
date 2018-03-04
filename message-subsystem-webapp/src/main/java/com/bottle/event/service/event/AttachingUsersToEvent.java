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
public class AttachingUsersToEvent {
    private EventStore eventStore;
    private UserStore userStore;

    @Autowired
    public AttachingUsersToEvent(EventStore eventStore, UserStore userStore) {
        this.eventStore = eventStore;
        this.userStore = userStore;
    }

    public String addUser(IdEventAndUserRequestDTO idEventAndUserRequestDTO) {
        try {
            Event event = eventStore.getById(UUID.fromString(idEventAndUserRequestDTO.getIdEvent()));
            User user = userStore.getById(UUID.fromString(idEventAndUserRequestDTO.getIdUser()));

            event.getUsers().add(user);
            user.getEvents().add(event);
            eventStore.createOrUpdate(event);
            userStore.createOrUpdate(user);
            user = userStore.getById(UUID.fromString(idEventAndUserRequestDTO.getIdUser()));
            System.out.println(event.getUsers().size());
            System.out.println(user.getEvents().size());

            event.getUsers().remove(user);
            user.getEvents().remove(event);
            eventStore.createOrUpdate(event);
            userStore.createOrUpdate(user);
            user = userStore.getById(UUID.fromString(idEventAndUserRequestDTO.getIdUser()));
            System.out.println(event.getUsers().size());
            System.out.println(user.getEvents().size());
            return "complete";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
