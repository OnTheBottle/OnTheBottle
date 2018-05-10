package com.bottle.service.event;

import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import com.bottle.model.repository.EventRepository;
import com.bottle.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Service
public class EntityBinder {
    private EventRepository eventRepository;
    private UserRepository userRepository;

    @Autowired
    public EntityBinder(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public String addUserToEvent(UUID idEvent, UUID idUser) {
        Event event = eventRepository.getOne(idEvent);
        User user = userRepository.getOne(idUser);

        if (event.getUsers().size() != 0 && event.getIsActive()) {
            event.getUsers().add(user);
            user.getEvents().add(event);
            eventRepository.save(event);
            return "Ok";
        } else {
            return "Closed";
        }
    }

    public void addUserToEvent(Event event, User user) {
        event.getUsers().add(user);
        user.getEvents().add(event);
        eventRepository.save(event);
    }

    public void deleteUserFromEvent(UUID idEvent, UUID idUser) {
        Event event = eventRepository.getOne(idEvent);
        User user = userRepository.getOne(idUser);

        event.getUsers().remove(user);
        user.getEvents().remove(event);

        if (user.equals(event.getOwner())) {
            User owner = getRandomOwner(event.getUsers());
            if (owner != null) {
                event.setOwner(owner);
            } else {
                event.setOwner(null);
                event.setIsActive(false);
            }
        }

        eventRepository.save(event);
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
