package com.bottle.temp.messenger.repository;

import com.bottle.temp.messenger.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MessageRepository extends CrudRepository<Message, UUID> {
    Message getByMessageId(UUID messageId);
}
