package com.bottle.service;

import com.bottle.model.DTO.Request.EventDTO;
import com.bottle.model.DTO.Response.EventResponseDTO;
import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class Mapper {
    public List<EventResponseDTO> eventsToEventDTO(List<Event> events, User user) {
        List<EventResponseDTO> eventsInfo = new ArrayList<>();
        events.forEach(e -> eventsInfo.add(eventToEventDTO(e, user, null)));
        return eventsInfo;
    }

    public void eventDTOToEvent(EventDTO eventDTO, Event event) {
        event.setTitle(eventDTO.getTitle());
        event.setText(eventDTO.getText());
        event.setStartTime(Utilities.formatDate(eventDTO.getStartTime()));
        event.setEndTime(Utilities.formatDate(eventDTO.getEndTime()));
    }

    public EventResponseDTO eventToEventDTO(Event event, User user, List<User> friends) {
        Set<User> usersEvent = event.getUsers();
        List<User> friendsPreliminary = new ArrayList<>();
        List<User> usersPreliminary = new ArrayList<>();
        int index = 0;

        EventResponseDTO eventResponseDTO = new EventResponseDTO();
        eventResponseDTO.setId(event.getId());
        eventResponseDTO.setTitle(event.getTitle());
        eventResponseDTO.setText(event.getText());
        eventResponseDTO.setStartTime(event.getStartTime());
        eventResponseDTO.setEndTime(event.getEndTime());
        eventResponseDTO.setPlace(event.getPlace());
        eventResponseDTO.setMember(usersEvent.contains(user));
        eventResponseDTO.setActive(event.getIsActive());
        eventResponseDTO.setOwner(event.getOwner());

        if (friends != null) {
            for (User friend : friends) {
                if (index == 6) break;
                if (usersEvent.contains(friend)) {
                    friendsPreliminary.add(friend);
                    index++;
                    usersEvent.remove(friend);
                }
            }
        }

        for (User userEvent : usersEvent) {
            if (index == 6) break;
            usersPreliminary.add(userEvent);
            index++;
        }

        eventResponseDTO.setFriends(friendsPreliminary);
        eventResponseDTO.setFriendsCounter(friendsPreliminary.size());
        eventResponseDTO.setUsers(usersPreliminary);
        eventResponseDTO.setUsersCounter(event.getUsersCounter());

        return eventResponseDTO;
    }
}
