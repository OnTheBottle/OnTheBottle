package com.bottle.chat.mapper;

import com.bottle.chat.DTO.ChatMessageDTO;
import com.bottle.chat.entity.ChatMessage;
import com.bottle.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageMapper {

    public static List<ChatMessageDTO> mapMessagesToChatDTOs(List<ChatMessage> messages) {
        List<ChatMessageDTO> messagesDTO = new ArrayList<ChatMessageDTO>();
        for (ChatMessage message : messages) {
            messagesDTO.add(mapToChatMessageDTO(message));
        }
        return messagesDTO;
    }

    public static ChatMessage mapChatDTOtoMessage(ChatMessageDTO messageDTO) {
        return new ChatMessage(
                // only need the id for mapping
                new User(messageDTO.getSenderId()),
                new User(messageDTO.getRecipientId()),
                messageDTO.getContent()
        );
    }

    public static ChatMessageDTO mapToChatMessageDTO(ChatMessage message) {
        ChatMessageDTO messageDTO = new ChatMessageDTO();
        messageDTO.setContent(message.getContent());
        messageDTO.setTime(message.getTime());
        messageDTO.setSenderId(message.getSender().getId());
        messageDTO.setRecipientId(message.getRecipient().getId());
        return messageDTO;
    }
}
