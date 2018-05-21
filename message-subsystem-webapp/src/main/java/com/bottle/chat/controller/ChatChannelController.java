package com.bottle.chat.controller;

import com.bottle.chat.DTO.ChatChannelInitializationDTO;
import com.bottle.chat.DTO.ChatMessageDTO;
import com.bottle.chat.DTO.EstablishedChatChannelDTO;
import com.bottle.chat.service.ChatService;
import com.bottle.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@Controller
public class ChatChannelController {
    @Autowired
    private ChatService chatService;

    @MessageMapping("/private.chat.{channelId}")
    @SendTo("/topic/private.chat.{channelId}")
    public ChatMessageDTO chatMessage(@DestinationVariable String channelId, ChatMessageDTO message) {
        chatService.submitMessage(message);
        return message;
    }

    @RequestMapping(value = "/chat/private-chat/channel", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public EstablishedChatChannelDTO establishChatChannel(@RequestBody ChatChannelInitializationDTO chatChannelInitialization) {
        UUID channelId = chatService.establishChatSession(chatChannelInitialization);

        return new EstablishedChatChannelDTO(
                channelId,
                chatChannelInitialization.getUserIdOne(),
                chatChannelInitialization.getUserIdTwo()
        );
    }

    @RequestMapping(value = "/chat/private-chat/channel/{channelId}", method = RequestMethod.GET, produces = "application/json")
    public List<ChatMessageDTO> getExistingChatMessages(@PathVariable("channelId") UUID channelId) {
        return chatService.getExistingChatMessages(channelId);
    }
}