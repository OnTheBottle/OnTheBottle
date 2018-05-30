package com.bottle.chat.service;

import com.bottle.chat.DTO.*;
import com.bottle.chat.entity.ChatChannel;
import com.bottle.chat.entity.ChatMessage;
import com.bottle.chat.entity.ChatTime;
import com.bottle.chat.mapper.ChatMessageMapper;
import com.bottle.chat.repository.ChatChannelRepository;
import com.bottle.chat.repository.ChatMessageRepository;
import com.bottle.chat.repository.ChatTimeRepository;
import com.bottle.client.UserSubsystemClient;
import com.bottle.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ChatService {

    private ChatChannelRepository chatChannelRepository;
    private ChatMessageRepository chatMessageRepository;
    private ChatTimeRepository chatTimeRepository;
    private UserService userService;
    private UserSubsystemClient client;
    private final int MAX_PAGABLE_CHAT_MESSAGES = 100;

    @Autowired
    public ChatService(
            ChatChannelRepository chatChannelRepository,
            ChatMessageRepository chatMessageRepository,
            ChatTimeRepository chatTimeRepository,
            UserSubsystemClient client,
            UserService userService) {
        this.chatChannelRepository = chatChannelRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.chatTimeRepository = chatTimeRepository;
        this.client = client;
        this.userService = userService;
    }

    private UUID getExistingChannel(UUID senderId, UUID recipientId) {
        List<ChatChannel> channel = chatChannelRepository
                .findExistingChannels(senderId, recipientId);

        return (channel != null && !channel.isEmpty()) ? channel.get(0).getId() : null;
    }

    public ChatChannelDTO initChannel(InitChatChannelDTO initChatChannelDTO){
        UUID senderId = initChatChannelDTO.getSenderId();
        UUID recipientId = initChatChannelDTO.getRecipientId();
        String token = initChatChannelDTO.getToken();
        UUID channelId = this.getChannel(senderId, recipientId);
        Map firstUser = client.getUser(senderId, token);
        Map secondUser = client.getUser(recipientId, token);
        return new ChatChannelDTO(channelId, firstUser, secondUser);
    }

    private UUID newChatSession(UUID senderId, UUID recipientId) {
        ChatChannel channel = new ChatChannel(
                userService.getUser(senderId),
                userService.getUser(recipientId)
        );
        chatChannelRepository.save(channel);

        ChatTime chatTimeSender = new ChatTime(
                channel.getId(),
                senderId
        );
        ChatTime chatTimeRecipient = new ChatTime(
                channel.getId(),
                recipientId
        );
        chatTimeRepository.save(chatTimeSender);
        chatTimeRepository.save(chatTimeRecipient);

        return channel.getId();
    }

    private UUID getChannel(UUID senderId, UUID recipientId) {
        if (senderId == recipientId) {
            return null;
        }
        UUID uuid = getExistingChannel(senderId, recipientId);
        return (uuid != null) ? uuid : newChatSession(senderId, recipientId);
    }

    public ChatMessageDTO submitMessage(ChatMessageDTO messageDTO) {
        ChatMessage message = ChatMessageMapper.mapChatDTOtoMessage(messageDTO);
        chatMessageRepository.save(message);
        return ChatMessageMapper.mapToChatMessageDTO(message);
    }

    public void setReadingTime(UUID authId, ReadingTimeDTO readingTimeDTO) {
        UUID channelId = readingTimeDTO.getChannelId();
        Date time = readingTimeDTO.getTime();
        chatTimeRepository.setReadingTime(authId, channelId, time);
    }

    public void setReadingTime(UUID authId, UUID channelId, Date time) {
//        System.out.println("setReadingTime time: " + time);
//        System.out.println("setReadingTime authId: " + authId);
//        System.out.println("setReadingTime channelId: " + channelId);
        chatTimeRepository.setReadingTime(authId, channelId, time);
    }

    public int getUnreadCount(UUID authId, UUID interlocutorId) {
        UUID channelId = chatChannelRepository.getChannelId(authId, interlocutorId);
        Date time = chatTimeRepository.getReadingTime(authId, channelId);
        System.out.println("getUnreadCount authId: " + authId);
        System.out.println("getUnreadCount interlocutorId: " + interlocutorId);
        System.out.println("getUnreadCount time: " + time);
        int count = chatMessageRepository.getNumberOfUnreadMessages(authId, interlocutorId, time);
        //int count = chatMessageRepository.getNumberOfUnreadMessages(authId, time);
        System.out.println("getUnreadCount count: " + count);
        return count;
    }

/*
        User senderUser = userService.getUser(chatMessage.getSender().getId());
        User recipientUser = userService.getUser(chatMessage.getRecipient().getId());

        userService.notifyUser(recipientUser,
                new NotificationDTO(
                        "ChatMessageNotification",
                        senderUser.getId() + " has sent you a message",
                        chatMessage.getSender().getId()
                )
        );
*/


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