package com.bottle.messenger.hibernate.factory;

import com.bottle.messenger.hibernate.model.Conversation;
import com.bottle.messenger.hibernate.model.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessengerDao {
    List<Conversation> getConversationsByUserId(int userId) throws SQLException;
    Message getLastMessageByConversationId(int conversationId) throws SQLException;
    List<Message> getMessageListByConversationId(int conversationId) throws SQLException;
    void addMessage(Message message) throws SQLException;
    void addConversation(Conversation conversation) throws SQLException;
}