package com.bottle.messenger.repository;

import com.bottle.messenger.entity.Conversation;
import com.bottle.messenger.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ConversationRepository extends CrudRepository<Conversation, UUID> {
    Conversation getByConversationId(UUID conversationId);
}
