package com.bottle.service.user;

import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import com.bottle.model.repository.UserRepository;
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

    public void createUser(User user) {
        getterUser.saveUser(user);
    }

    /*public List<UUID> getAllUsersId() {
        return getterUser.getAll();
    }*/

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

    //added by Alexander.Cherba
    public void addUserById(UUID id) {
        User user = new User();
        user.setId(id);
        getterUser.saveUser(user);
    }

    public boolean isExistUserById(UUID id){
        return getterUser.isExistUserById(id);
    }
}
