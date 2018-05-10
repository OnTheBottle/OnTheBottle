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

    public boolean isFriend(UUID authId, UUID userId) {
        User auth = userRepository.getUserById(authId);
        User user = userRepository.getUserById(userId);
        boolean isTrue = auth.getFriends().contains(user)&&user.getFriends().contains(auth);
        System.out.println("isFriend: " + isTrue);
        return isTrue;
    }


        public Set<User> getFriends(UUID authId, UUID userId, String select) {
        User auth = userRepository.getUserById(authId);
        Set<User> friends = new HashSet<>();

        switch (select) {
            case "notconfirmed":
                friends = userRepository.getAllByFriendsContaining(auth);
                friends.removeAll(auth.getFriends());
                break;
            case "requested":
                friends = userRepository.getAllByFriendsContaining(auth);
                Set<User> temp = auth.getFriends();
                temp.removeAll(friends);
                friends = temp;
                break;
            case "confirmed":
                User user = userRepository.getUserById(userId);
                Set<User> userFriends = user.getFriends();
                Set<User> friendsTemp = userRepository.getAllByFriendsContaining(user);
                for (User friend : userFriends) {
                    if (friendsTemp.contains(friend)) {
                        if (!friend.equals(auth)) friends.add(friend);
                    }
                }
                break;
        }
        return setFriendsStatus(friends, auth);
    }

    private Set<User> setFriendsStatus(Set<User> friends, User auth) {

        for (User friend : friends) {
            String status = "";
            int count = 1;
            if (friend.getFriends().contains(auth)) count = count + 2;
            if (auth.getFriends().contains(friend)) count = count + 4;

            switch (count) {
                case 1:
                    status = "unknown";
                    break;
                case 3:
                    status = "notconfirmed";
                    break;
                case 5:
                    status = "requested";
                    break;
                case 7:
                    status = "confirmed";
                    break;
            }
            friend.setFriendStatus(status);
            System.out.println("friend with status - " + status + ":\n" + friend);
        }
        return friends;
    }
}



