package com.bottle.service.user;

import com.bottle.Properties;
import com.bottle.model.DTO.EventDTO;
import com.bottle.model.DTO.request.IdRequestDTO;
import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Random random = new Random();
        UUID id = UUID.randomUUID();
        String name = "TestName" + random.nextInt(100);
        String surname = "TestSurname" + random.nextInt(100);
        String avatar = Properties.SUBMESSAGEPATH + "/images/68572.jpeg";
        return getterUser.createUser(id, name, surname, avatar);
    }

    public List<UUID> getAllUsersId() {
        return getterUser.getAllId();
    }

    public List<User> getAllUsers() {
        return getterUser.getAll();
    }

    public Map<UUID, String> getAllEventsIdFromUser(UUID id) {
        Map<UUID, String> allEvents = new HashMap<>();
        User user = getterUser.getUser(id);

        for (Event event : user.getEvents()) {
            allEvents.put(event.getId(), event.getTitle());
        }

        return allEvents;
    }

    public void addFriend(UUID userIdOne, UUID userIdTwo) {
        User userOne = getUser(userIdOne);
        User userTwo = getUser(userIdTwo);
        userOne.getFriends().add(userTwo);
        userTwo.getFriends().add(userOne);
        getterUser.saveUser(userOne);
    }
}
