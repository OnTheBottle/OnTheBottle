package com.bottle.messenger.repository;

import com.bottle.messenger.entity.Conversation;
import com.bottle.messenger.entity.PersonToConversation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PersonToConversationRepository extends CrudRepository<PersonToConversation, UUID> {
   
}
