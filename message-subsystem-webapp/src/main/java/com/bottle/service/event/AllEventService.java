package com.bottle.service.event;

import com.bottle.client.UserSubsystemClient;
import com.bottle.model.DTO.*;
import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import com.bottle.model.repository.EventRepository;
import com.bottle.service.user.GetterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AllEventService {
    private RegistrationEvent registrationEvent;
    private GetterEvent getterEvent;
    private CloseEvent closeEvent;
    private EntityBinder entityBinder;
    private GetterUser getterUser;
    private EventRepository eventRepository;
    private UserSubsystemClient client;

    @Autowired
    public AllEventService(RegistrationEvent registrationEvent, GetterEvent getterEvent, CloseEvent closeEvent,
                           EntityBinder entityBinder, GetterUser getterUser, EventRepository eventRepository,
                           UserSubsystemClient client) {
        this.registrationEvent = registrationEvent;
        this.getterEvent = getterEvent;
        this.closeEvent = closeEvent;
        this.entityBinder = entityBinder;
        this.getterUser = getterUser;
        this.eventRepository = eventRepository;
        this.client = client;
    }

    public void createEvent(EventDTO eventDTO) {
        registrationEvent.createEvent(eventDTO);
    }

    public Set<EventResponseDTO> getEvents(OptionsDTO options, int eventsPage, UUID userId) {
        User user = getterUser.getUser(userId);
        if (options.isAllEvents()) {
            if (options.isActiveEvents()) {
                if (options.isOwnerEvents()) {
                    return getSetResponseEventsInfo(getterEvent.getOwnerFromUser(user, eventsPage), user);
                }
                return getSetResponseEventsInfo(checkPassedEvents(getterEvent.getActiveEvents(eventsPage)), user);
            }
            return getSetResponseEventsInfo(getterEvent.getPassedEvents(eventsPage), user);
        }

        if (options.isActiveEvents()) {
            if (options.isOwnerEvents()) {
                return getSetResponseEventsInfo(getterEvent.getOwnerFromUser(user, eventsPage), user);
            }
            return getSetResponseEventsInfo(checkPassedEvents(getterEvent.getActiveFromUser(userId, eventsPage)), user);
        }
        return getSetResponseEventsInfo(getterEvent.getPassedFromUser(userId, eventsPage), user);
    }

    public void updateEvent(EventDTO eventDTO) {
        registrationEvent.updateEvent(eventDTO);
    }

    public void closeEvent(UUID id) {
        closeEvent.closeEvent(id);
    }

    public EventResponseDTO getEvent(UUID idEvent, UUID idUser, String token) throws NotEventException {
        Event event = getterEvent.getEvent(idEvent);
        User user = getterUser.getUser(idUser);
        List<User> users = new ArrayList<>();
        List<Map> friends = client.getFriends(idUser, token);
        for (Map<String, String> friend : friends) {
            users.add(getterUser.getUser(UUID.fromString(friend.get("id"))));
        }

        return setResponseEventInfo(event, user, users);
    }

    public String addUser(UUID idEvent, UUID idUser) {
        return entityBinder.addUserToEvent(idEvent, idUser);
    }

    public void deleteUser(UUID idEvent, UUID idUser) {
        entityBinder.deleteUserFromEvent(idEvent, idUser);
    }

    private Set<Event> checkPassedEvents(Set<Event> events) {
        Date today = new Date();
        for (Event event : events) {
            if (event.getEndTime().before(today)) {
                event.setIsActive(false);
                eventRepository.save(event);
            }
        }
        return events;
    }

    private Set<EventResponseDTO> getSetResponseEventsInfo(Set<Event> events, User user) {
        Set<EventResponseDTO> eventsInfo = new HashSet<>();
        for (Event event : events) {
            eventsInfo.add(setResponseEventInfo(event, user, null));
        }
        return eventsInfo;
    }

    private EventResponseDTO setResponseEventInfo(Event event, User user, List<User> friends) {
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