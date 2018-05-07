package com.bottle.service;

import com.bottle.entity.User;
import com.bottle.model.dto.UserDTO;
import com.bottle.repository.UserRepository;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class FriendService {

    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public FriendService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void removeRelationship(UUID firstId, UUID secondId) {
        removeOneWayRelation(firstId, secondId);
        removeOneWayRelation(secondId, firstId);
    }

    private void removeOneWayRelation(UUID firstId, UUID secondId) {
        User firstUser = userRepository.getUserById(firstId);
        User secondUser = userRepository.getUserById(secondId);
        firstUser.getFriends().remove(secondUser);
        userRepository.save(firstUser);
    }

    public void addTwoWayRelation(UUID firstId, UUID secondId) {
        addOneWayRelation(firstId, secondId);
        addOneWayRelation(secondId, firstId);
    }

    public void addOneWayRelation(UUID firstId, UUID secondId) {
        User firstUser = userRepository.getUserById(firstId);
        User secondUser = userRepository.getUserById(secondId);
        firstUser.getFriends().add(secondUser);
        userRepository.save(firstUser);
    }

    public Set<UserDTO> getConfirmedFriends(UUID id) {
        String friendStatus = "confirmed";
        User user = userRepository.getUserById(id);
        Set<User> friends = user.getFriends();
        Set<User> confirmedFriends = new HashSet<>();

        for (User friend : friends) {
            if (friend.getFriends().contains(user)) {
                confirmedFriends.add(friend);
            }
        }
        return userService.getUsersDTO(confirmedFriends, friendStatus);
    }

    public Set<UserDTO> getNotConfirmedFriends(UUID id) {
        String friendStatus = "notConfirmed";
        User user = userRepository.getUserById(id);
        Set<User> userFriends = user.getFriends();
        Set<User> maybeFriends = userRepository.getAllByFriendsContaining(user);
        maybeFriends.removeAll(userFriends);
        return userService.getUsersDTO(maybeFriends, friendStatus);
    }
}


