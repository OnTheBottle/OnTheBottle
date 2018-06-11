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

import java.util.*;

@Service
public class ChatService {

    static Date nullDate = new Date(0);

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

    public ChatChannelDTO initChannel(InitChatChannelDTO initChatChannelDTO) {
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

    public Map<UUID, UUID> getChannelMap(UUID authId, List<UUID> interlocutorIds) {
        Map<UUID, UUID> channelMap = new HashMap<>();
        interlocutorIds.forEach(interlocutorId -> {
            channelMap.put(interlocutorId, getChannel(authId, interlocutorId));
        });
        return channelMap;
    }

    public ChatMessageDTO submitMessage(UUID channelId, ChatMessageDTO messageDTO) {
        ChatMessage message = ChatMessageMapper.mapChatDTOtoMessage(messageDTO);
        message.setChannelId(channelId);
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

    public int getNumberNewChatMessages(UUID authId, UUID interlocutorId) {
        if (authId == null || interlocutorId == null) return 0;

        UUID channelId = chatChannelRepository.getChannelId(authId, interlocutorId);
        Date time = chatTimeRepository.getReadingTime(authId, channelId);
        int number = chatMessageRepository.getNumberOfNewMessages(channelId, time);
        System.out.println("getNumberNewChatMessages number: " + number);
        return number;
    }

/*
    public int getNumberNewMessages(UUID authId) {
        if (authId == null) return 0;

        int count = 0;
        Set<UUID> channelIds = chatChannelRepository.getChannelIds(authId);

        for (UUID channelId : channelIds) {
            Date time = chatTimeRepository.getReadingTime(authId, channelId);
            UUID interlocutorId = chatChannelRepository.getInterlocutorId(authId, channelId);
            count += chatMessageRepository.getNumberOfUnreadMessages(authId, interlocutorId, time);
        }
        System.out.println("getNumberAllUnreadMessages count: " + count);
        return count;
    }
*/

    public ChatNotificationDTO getChatNotificationDTO(UUID authId) {

        if (authId == null) return null;

        Map<UUID, Integer> map = new HashMap<>();
        Set<UUID> channelIds = chatChannelRepository.getChannelIds(authId);

        int count = 0;

        for (UUID channelId : channelIds) {
            Date time;
            time = chatTimeRepository.getReadingTime(authId, channelId);
            if (time == null) time = ChatService.nullDate;
            int number = chatMessageRepository.getNumberOfNewMessages(channelId, time);
            map.put(channelId, number);
            count += number;
        }
        return new ChatNotificationDTO(count, map);
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