package com.bottle.messenger.controller;

import com.bottle.messenger.dto.request.RequestGetConversationListDTO;
import com.bottle.messenger.dto.response.ResponseGetConversationListDTO;
import com.bottle.messenger.repository.ConversationRepository;
import com.bottle.messenger.repository.MessageRepository;
import com.bottle.messenger.dto.request.RequestGetConversationDTO;
import com.bottle.messenger.dto.request.RequestSendMessageDTO;
import com.bottle.messenger.dto.response.ResponseGetConversationDTO;
import com.bottle.messenger.dto.response.ResponseSendMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessengerController {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public MessengerController(ConversationRepository conversationRepository, MessageRepository messageRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/get_conversation")
    @ResponseBody
    private ResponseGetConversationDTO getConversation(RequestGetConversationDTO request){
        return new ResponseGetConversationDTO();
    }

    @GetMapping("/get_conversation_list")
    @ResponseBody
    private ResponseGetConversationListDTO getConversationList(RequestGetConversationListDTO request){
        return new ResponseGetConversationListDTO();
    }

    @PostMapping("/post_message")
    @ResponseBody
    private ResponseSendMessageDTO postMessage(RequestSendMessageDTO request){
        return new ResponseSendMessageDTO();
    }
}