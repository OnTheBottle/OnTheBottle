package com.bottle.chat.controller;

import com.bottle.chat.DTO.ChatChannelDTO;
import com.bottle.chat.DTO.ChatMessageDTO;
import com.bottle.chat.DTO.InitChatChannelDTO;
import com.bottle.chat.service.ChatService;
import com.bottle.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatChannelController {

    private final ChatService chatService;
    private final AuthService authService;

    @Autowired
    public ChatChannelController(ChatService chatService, AuthService authService) {
        this.chatService = chatService;
        this.authService = authService;
    }

    @MessageMapping("/private.chat.{channelId}")
    @SendTo("/topic/private.chat.{channelId}")
    public ChatMessageDTO chatMessage(@DestinationVariable String channelId, ChatMessageDTO message) {
        chatService.submitMessage(message);
        return message;
    }

    @RequestMapping(value = "/chat/private-chat/channel", method = RequestMethod.POST)
    @ResponseBody
    public ChatChannelDTO initChatChannel(InitChatChannelDTO initChatChannelDTO) {
        if (authService.isValidToken(initChatChannelDTO.getToken())) {
            return chatService.initChannel(initChatChannelDTO);
        }
        return null;
    }

    @RequestMapping(value = "/chat/private-chat/channel/{channelId}", method = RequestMethod.POST)
    public List<ChatMessageDTO> getExistingChatMessages(@PathVariable("channelId") UUID channelId) {
        return chatService.getExistingChatMessages(channelId);
    }
}