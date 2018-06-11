package com.bottle.chat.websocket;

import com.bottle.chat.DTO.ChatNotificationDTO;
import com.bottle.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final SocketHandler socketHandler;
    private ChatService chatService;

    @Autowired
    public Scheduler(SocketHandler socketHandler, ChatService chatService) {
        this.socketHandler = socketHandler;
        this.chatService = chatService;
    }

    @Scheduled(fixedDelay = 3000)
    public void sendNewMessageNumbersScheduler() throws Exception {
        if (socketHandler.getSession() != null && socketHandler.getSession().isOpen()) {
            ChatNotificationDTO notificationDTO = chatService.getChatNotificationDTO(socketHandler.getAuthId());
            socketHandler.sendNumberNewMessages(notificationDTO);
        }
    }

}
