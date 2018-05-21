package com.bottle.chat.mapper;

import com.bottle.chat.DTO.ChatMessageDTO;
import com.bottle.chat.entity.ChatMessage;
import com.bottle.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageMapper {
    public static List<ChatMessageDTO> mapMessagesToChatDTOs(List<ChatMessage> chatMessages) {
        List<ChatMessageDTO> dtos = new ArrayList<ChatMessageDTO>();

        for (ChatMessage chatMessage : chatMessages) {
            dtos.add(
                    new ChatMessageDTO(
                            chatMessage.getContent(),
                            chatMessage.getAuthor().getId(),
                            chatMessage.getRecipient().getId()
                    )
            );
        }
        return dtos;
    }

    public static ChatMessage mapChatDTOtoMessage(ChatMessageDTO dto) {
        return new ChatMessage(
                // only need the id for mapping
                new User(dto.getFromUserId()),
                new User(dto.getToUserId()),
                dto.getContents()
        );
    }
}
