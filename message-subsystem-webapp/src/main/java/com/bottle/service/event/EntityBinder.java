package com.bottle.service.event;

import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import com.bottle.model.repository.EventRepository;
import com.bottle.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addUserToEvent(UUID idEvent, UUID idUser) {
        Event event = eventRepository.getOne(idEvent);
        User user = userRepository.getOne(idUser);

        event.getUsers().add(user);
        user.getEvents().add(event);
        eventRepository.save(event);
    }

    public void addUserToEvent(Event event, User user) {
        event.getUsers().add(user);
        user.getEvents().add(event);
        eventRepository.save(event);
    }

    public String deleteUserFromEvent(UUID idEvent, UUID idUser) {
        Event event = eventRepository.getOne(idEvent);
        User user = userRepository.getOne(idUser);

        if (user.equals(event.getOwner())) {
            return "Can't delete owner";
        }

        event.getUsers().remove(user);
        user.getEvents().remove(event);
        eventRepository.save(event);

        return "complete";
    }
}
