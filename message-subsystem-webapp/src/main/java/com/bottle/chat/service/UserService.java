package com.bottle.chat.service;

import com.bottle.chat.DTO.NotificationDTO;
import com.bottle.model.entity.User;
import com.bottle.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private SimpMessagingTemplate simpMessagingTemplate;
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, SimpMessagingTemplate simpMessagingTemplate) {
        this.userRepository = userRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public User getUser(UUID userId){
        return userRepository.getOne(userId);
    }

    public void notifyUser(User recipientUser, NotificationDTO notification) {
            simpMessagingTemplate
                    .convertAndSend("/topic/user.notification." + recipientUser.getId(), notification);
    }

}
