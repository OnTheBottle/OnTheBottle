package com.bottle.messenger.repository;

import com.bottle.messenger.entity.Conversation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ConversationRepository extends CrudRepository<Conversation, UUID> {
}
