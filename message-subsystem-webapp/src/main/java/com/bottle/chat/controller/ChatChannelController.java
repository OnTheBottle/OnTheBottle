package com.bottle.chat.controller;

import com.bottle.chat.DTO.ChatChannelDTO;
import com.bottle.chat.DTO.ChatMessageDTO;
import com.bottle.chat.DTO.InitChatChannelDTO;
import com.bottle.chat.entity.ChatMessage;
import com.bottle.chat.mapper.ChatMessageMapper;
import com.bottle.chat.service.ChatService;
import com.bottle.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatChannelController {

    private final ChatService chatService;
    private final AuthService authService;
    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public ChatChannelController(ChatService chatService, AuthService authService, SimpMessageSendingOperations messagingTemplate) {
        this.chatService = chatService;
        this.authService = authService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/private.chat.{channelId}")
    @SendTo("/topic/private.chat.{channelId}")
    public ChatMessageDTO chatMessage(@DestinationVariable String channelId, ChatMessageDTO messageDTO) {
        return chatService.submitMessage(messageDTO);
    }

    @RequestMapping(value = "/chat/channel/init", method = RequestMethod.POST)
    @ResponseBody
    public ChatChannelDTO initChatChannel(InitChatChannelDTO initChatChannelDTO) {
        if (authService.isValidToken(initChatChannelDTO.getToken())) {
            return chatService.initChannel(initChatChannelDTO);
        }
        return null;
    }

/*
    @MessageMapping("/private.chat.{channelId}")
    @SendTo("/topic/private.chat.{channelId}")
    public ChatMessageDTO chatMessage(@DestinationVariable String channelId, ChatMessageDTO messageDTO) {
        chatService.submitMessage(messageDTO);
        return messageDTO;
    }
*/

/*
    @RequestMapping(value = "/chat/channel/getmessages", method = RequestMethod.POST)
    @ResponseBody
    public void getExistingChatMessages(
            @RequestParam(name = "token") String token,
            @RequestParam(name = "channelId") UUID channelId) {
        //@PathVariable("channelId") UUID channelId){
        if (authService.isValidToken(token)) {
            List messagesDTO = chatService.getExistingChatMessages(channelId);

            //messagesDTO.stream().sorted(Comparator.reverseOrder()).forEachOrdered(messageDTO ->messagingTemplate.convertAndSend("/topic/private.chat." + channelId, messageDTO));
            messagesDTO.forEach(messageDTO ->messagingTemplate.convertAndSend("/topic/private.chat." + channelId, messageDTO));
            //messagingTemplate.convertAndSend("/topic/private.chat." + channelId, messagesDTO);
        }
    }
*/
    @RequestMapping(value = "/chat/channel/getmessages", method = RequestMethod.POST)
    @ResponseBody
    public List<ChatMessageDTO> getExistingChatMessages(
            @RequestParam(name = "token") String token,
            @RequestParam(name = "channelId") UUID channelId) {
        if (authService.isValidToken(token)) {
            List messages = chatService.getExistingChatMessages(channelId);
            return messages;
        }
        return null;
    }
}