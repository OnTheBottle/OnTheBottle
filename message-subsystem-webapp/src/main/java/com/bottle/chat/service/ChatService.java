package com.bottle.chat.service;

import com.bottle.chat.DTO.ChatChannelInitializationDTO;
import com.bottle.chat.DTO.ChatMessageDTO;
import com.bottle.chat.DTO.NotificationDTO;
import com.bottle.chat.entity.ChatChannel;
import com.bottle.chat.entity.ChatMessage;
import com.bottle.chat.mapper.ChatMessageMapper;
import com.bottle.chat.repository.ChatChannelRepository;
import com.bottle.chat.repository.ChatMessageRepository;
import com.bottle.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatService {

    private ChatChannelRepository chatChannelRepository;
    private ChatMessageRepository chatMessageRepository;
    private UserService userService;
    private final int MAX_PAGABLE_CHAT_MESSAGES = 100;

    @Autowired
    public ChatService(
            ChatChannelRepository chatChannelRepository,
            ChatMessageRepository chatMessageRepository,
            UserService userService) {
        this.chatChannelRepository = chatChannelRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.userService = userService;
    }

    private UUID getExistingChannel(ChatChannelInitializationDTO chatChannelInitializationDTO) {
        List<ChatChannel> channel = chatChannelRepository
                .findExistingChannels(
                        chatChannelInitializationDTO.getUserIdOne(),
                        chatChannelInitializationDTO.getUserIdTwo()
                );

        return (channel != null && !channel.isEmpty()) ? channel.get(0).getId() : null;
    }

    private UUID newChatSession(ChatChannelInitializationDTO chatChannelInitializationDTO) {
        ChatChannel channel = new ChatChannel(
                userService.getUser(chatChannelInitializationDTO.getUserIdOne()),
                userService.getUser(chatChannelInitializationDTO.getUserIdTwo())
        );
        chatChannelRepository.save(channel);
        return channel.getId();
    }

    public UUID establishChatSession(ChatChannelInitializationDTO chatChannelInitializationDTO) {
        if (chatChannelInitializationDTO.getUserIdOne() == chatChannelInitializationDTO.getUserIdTwo()) {
            return null;
        }
        UUID uuid = getExistingChannel(chatChannelInitializationDTO);
        // If channel doesn't already exist, create a new one
        return (uuid != null) ? uuid : newChatSession(chatChannelInitializationDTO);
    }

    public void submitMessage(ChatMessageDTO chatMessageDTO) {
        ChatMessage chatMessage = ChatMessageMapper.mapChatDTOtoMessage(chatMessageDTO);

        chatMessageRepository.save(chatMessage);

        User senderUser = userService.getUser(chatMessage.getAuthor().getId());
        User recipientUser = userService.getUser(chatMessage.getRecipient().getId());

        userService.notifyUser(recipientUser,
                new NotificationDTO(
                        "ChatMessageNotification",
                        senderUser.getId() + " has sent you a message",
                        chatMessage.getAuthor().getId()
                )
        );
    }

    public List<ChatMessageDTO> getExistingChatMessages(UUID channelId) {
        ChatChannel channel = chatChannelRepository.findOne(channelId);

        List<ChatMessage> chatMessages =
                chatMessageRepository.getExistingChatMessages(
                        channel.getFirstUser().getId(),
                        channel.getSecondUser().getId(),
                        new PageRequest(0, MAX_PAGABLE_CHAT_MESSAGES)
                );
        return ChatMessageMapper.mapMessagesToChatDTOs(chatMessages);
    }
}