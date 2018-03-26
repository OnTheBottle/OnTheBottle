package com.bottle.messenger.repository;

import com.bottle.messenger.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends CrudRepository<Message, UUID> {
    Message getByMessageId(UUID messageId);
}
