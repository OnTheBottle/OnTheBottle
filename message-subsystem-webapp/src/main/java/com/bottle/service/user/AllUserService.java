package com.bottle.service.user;

import com.bottle.model.DTO.EventDTO;
import com.bottle.model.DTO.request.IdRequestDTO;
import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AllUserService {
    private GetterUser getterUser;

    @Autowired
    public AllUserService(GetterUser getterUser) {
        this.getterUser = getterUser;
    }

    public User getUser(UUID id) {
        return getterUser.getUser(id);
    }

    public User createUser() {
        return getterUser.createUser(UUID.randomUUID());
    }

    public List<UUID> getAllUsersId() {
        return getterUser.getAllId();
    }

    public Map<UUID, String> getAllEventsIdFromUser(UUID id) {
        Map<UUID, String> allEvents = new HashMap<>();
        User user = getterUser.getUser(id);

        for (Event event : user.getEvents()) {
            allEvents.put(event.getId(), event.getTitle());
        }

        return allEvents;
    }
}
