package temp.messenger.controller;

import temp.messenger.dto.request.RequestGetConversationListDTO;
import temp.messenger.dto.response.ResponseGetConversationListDTO;
import temp.messenger.repository.ConversationRepository;
import temp.messenger.repository.MessageRepository;
import temp.messenger.dto.request.RequestGetConversationDTO;
import temp.messenger.dto.request.RequestSendMessageDTO;
import temp.messenger.dto.response.ResponseGetConversationDTO;
import temp.messenger.dto.response.ResponseSendMessageDTO;
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