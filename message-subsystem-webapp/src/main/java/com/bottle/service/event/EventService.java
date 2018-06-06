package com.bottle.service.event;

import com.bottle.client.UserSubsystemClient;
import com.bottle.model.DTO.PostDTO;
import com.bottle.model.DTO.Request.EventDTO;
import com.bottle.model.DTO.Request.OptionsDTO;
import com.bottle.model.DTO.Response.EventResponseDTO;
import com.bottle.model.DTO.UsersDTO;
import com.bottle.model.entity.Event;
import com.bottle.model.entity.Place;
import com.bottle.model.entity.User;
import com.bottle.model.repository.EventRepository;
import com.bottle.model.repository.PlaceRepository;
import com.bottle.model.repository.UserRepository;
import com.bottle.service.Builder;
import com.bottle.service.Mapper;
import com.bottle.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class EventService {
    private EntityBinder entityBinder;
    private UserSubsystemClient client;
    private PostService allPostService;
    private Builder builder;
    private Mapper mapper;
    private UserRepository userRepository;
    private PlaceRepository placeRepository;
    private EventRepository eventRepository;
    private static final int EVENTS_COUNT = 6;

    @Autowired
    public EventService(EntityBinder entityBinder, EventRepository eventRepository, UserSubsystemClient client,
                        PostService allPostService, Builder builder, Mapper mapper,
                        UserRepository userRepository, PlaceRepository placeRepository) {
        this.entityBinder = entityBinder;
        this.eventRepository = eventRepository;
        this.client = client;
        this.allPostService = allPostService;
        this.builder = builder;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }

    public void createEvent(EventDTO eventDTO) {
        Event event = builder.buildEvent(eventDTO);
        Place place = placeRepository.getOne(eventDTO.getPlace());
        User owner = userRepository.getOne(eventDTO.getOwner());
        event.setPlace(place);
        event.setOwner(owner);
        entityBinder.addUserToEvent(event, owner);
        eventRepository.save(event);
        if (eventDTO.isAddPost()) allPostService.addPost(setPostInfo(event));
    }

    public List<EventResponseDTO> getEvents(OptionsDTO options, int eventsPage, String sortType, UUID userId) {
        User user = userRepository.getOne(userId);
        if (options.isAllEvents()) {
            if (options.isActiveEvents()) {
                if (options.isOwnerEvents()) {
                    return mapper.eventsToEventDTO(
                            eventRepository.getEventsByOwnerAndIsActiveTrue(
                                    user, getPageRequest(eventsPage, sortType)), user);
                }
                return mapper.eventsToEventDTO(checkPassedEvents(
                        eventRepository.findAllByIsActive(true, getPageRequest(eventsPage, sortType))), user);
            }
            return mapper.eventsToEventDTO(
                    eventRepository.findAllByIsActive(false, getPageRequest(eventsPage, sortType)), user);
        }

        if (options.isActiveEvents()) {
            if (options.isOwnerEvents()) {
                return mapper.eventsToEventDTO(eventRepository.getEventsByOwnerAndIsActiveTrue(
                        user, getPageRequest(eventsPage, sortType)), user);
            }
            return mapper.eventsToEventDTO(
                    checkPassedEvents(eventRepository.getEventsFromUserIsActive(
                            userId, true, getPageRequest(eventsPage, sortType))), user);
        }
        return mapper.eventsToEventDTO(eventRepository.getEventsFromUserIsActive(
                userId, false, getPageRequest(eventsPage, sortType)), user);
    }

    public void updateEvent(EventDTO eventDTO) {
        Event event = eventRepository.getOne(eventDTO.getId());
        Place place = placeRepository.getOne(eventDTO.getPlace());
        mapper.eventDTOToEvent(eventDTO, event);
        event.setPlace(place);
        eventRepository.save(event);
    }

    public void closeEvent(UUID id) {
        eventRepository.setEventStatus(id, false);
    }

    public EventResponseDTO getEvent(UUID idEvent, UUID idUser, String token) throws NotEventException {
        if (!eventRepository.exists(idEvent)) throw new NotEventException("No events with id: " + idEvent);
        Event event = eventRepository.getOne(idEvent);
        User user = userRepository.getOne(idUser);
        List<User> friends = new ArrayList<>();
        List<Map> users = client.getFriends(idUser, token); //TODO stream?
        for (Map<String, String> item : users) {
            friends.add(userRepository.getOne(UUID.fromString(item.get("id"))));
        }
        return mapper.eventToEventDTO(event, user, friends);
    }

    @Transactional
    public boolean addUser(UUID idEvent, UUID idUser) {
        Event event = eventRepository.getOne(idEvent);
        User user = userRepository.getOne(idUser);
        if (entityBinder.addUserToEvent(event, user)) {
            eventRepository.save(event);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteUser(UUID idEvent, UUID idUser) {
        Event event = eventRepository.getOne(idEvent);
        User user = userRepository.getOne(idUser);
        if (entityBinder.deleteUserFromEvent(event, user)) {
            eventRepository.save(event);
            return true;
        }
        return false;
    }

    public List<EventResponseDTO> searchEvents(String searchQuery, int eventsPage, UUID idUser) {
        User user = userRepository.getOne(idUser);
        return mapper.eventsToEventDTO(eventRepository.getAllByStringQuery(
                searchQuery, getPageRequest(eventsPage, "")), user);
    }

    public UsersDTO getUsersEvent(UUID eventId, UUID userId, String token) {
        Event event = eventRepository.getOne(eventId);
        Set<User> usersEvent = event.getUsers();

        List<User> friends = new ArrayList<>();
        List<Map<String,String>> friendsMaps = client.getFriends(userId, token);
        friendsMaps.forEach(friendsMap -> {
            User friend = userRepository.getOne(UUID.fromString(friendsMap.get("id")));
            if (usersEvent.contains(friend)) {
                friends.add(friend);
                usersEvent.remove(friend);
            }
        });
        UsersDTO result = new UsersDTO();
        result.setFriends(friends);
        result.setUsers(new ArrayList<>(usersEvent));

        return result;
    }

    private List<Event> checkPassedEvents(List<Event> events) {
        Date today = new Date();
        for (Event event : events) {
            if (event.getEndTime().before(today)) {
                event.setIsActive(false);
                eventRepository.save(event);
            }
        }
        return events;
    }

    private PostDTO setPostInfo(Event event) {
        PostDTO post = new PostDTO();

        post.setSecurity("Anybody views a post");
        post.setTitle(String.format("Я создал ивент: %s", event.getTitle()));
        post.setText(String.format("Я собираю людей %1$td.%1$tm в %tT! Собираемся в %s. " +
                        "Подробности по ссылке: \nhttp://localhost:8080/master.html#!/eventInfo/%s",
                event.getStartTime(), event.getPlace().getTitle(), event.getId()));
        post.setUserId(event.getOwner().getId());
        post.setUploadFiles(new ArrayList<>());
        return post;
    }

    private PageRequest getPageRequest(int eventsPage, String sortType) {
        switch (sortType) {
            case "title":
                return new PageRequest(eventsPage, EVENTS_COUNT, Sort.Direction.ASC, sortType);
            case "startTime":
                return new PageRequest(eventsPage, EVENTS_COUNT, Sort.Direction.DESC, sortType);
            case "text":
                return new PageRequest(eventsPage, EVENTS_COUNT, Sort.Direction.ASC, sortType);
            case "usersCounter":
                return new PageRequest(eventsPage, EVENTS_COUNT, Sort.Direction.DESC, sortType);
            default:
                return new PageRequest(eventsPage, EVENTS_COUNT);
        }
    }
}