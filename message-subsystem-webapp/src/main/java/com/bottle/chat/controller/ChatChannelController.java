package com.bottle.chat.controller;

import com.bottle.chat.DTO.ChatChannelDTO;
import com.bottle.chat.DTO.ChatMessageDTO;
import com.bottle.chat.DTO.InitChatChannelDTO;
import com.bottle.chat.DTO.ReadingTimeDTO;
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

import java.util.*;

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
    public ChatMessageDTO chatMessage(@DestinationVariable UUID channelId, ChatMessageDTO messageDTO) {
        return chatService.submitMessage(channelId, messageDTO);
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
    @RequestMapping(value = "/chat/channel/setTime", method = RequestMethod.POST)
    @ResponseBody
    public void setReadingTime(ReadingTimeDTO readingTimeDTO) {
        if (authService.isValidToken(readingTimeDTO.getToken())) {
            UUID authId = authService.getAuthId(readingTimeDTO.getToken());
            chatService.setReadingTime(authId, readingTimeDTO);
        }
    }
*/

    @RequestMapping(value = "/chat/channel/setTime", method = RequestMethod.POST)
    @ResponseBody
    public void setReadingTime(
            @RequestParam(name = "token") String token,
            @RequestParam(name = "channelId") UUID channelId,
            @RequestParam(name = "time") long time) {
        if (authService.isValidToken(token)) {
            UUID authId = authService.getAuthId(token);
            chatService.setReadingTime(authId, channelId, new Date(time));
        }
    }

    @RequestMapping(value = "/chat/channel/getUnreadCount", method = RequestMethod.POST)
    @ResponseBody
    public int getUnreadCount(
            @RequestParam(name = "token") String token,
            @RequestParam(name = "interlocutorId") UUID interlocutorId) {
        if (authService.isValidToken(token)) {
            UUID authId = authService.getAuthId(token);
            return chatService.getNumberNewChatMessages(authId, interlocutorId);
        }
        return 0;
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

    @RequestMapping(value = "/chat/channel/getChannels", method = RequestMethod.POST)
    @ResponseBody
    public Map<UUID, UUID> getChannels(
            @RequestParam(name = "token") String token,
            @RequestParam(name = "interlocutorIds") List<UUID> interlocutorIds) {
        if (authService.isValidToken(token)) {
            UUID authId = authService.getAuthId(token);
            Map<UUID, UUID> channelMap = chatService.getChannelMap(authId, interlocutorIds);
            return channelMap;
        }
        return null;
    }
}