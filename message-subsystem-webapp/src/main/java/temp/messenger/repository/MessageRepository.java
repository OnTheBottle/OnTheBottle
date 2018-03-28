package temp.messenger.repository;

import temp.messenger.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MessageRepository extends CrudRepository<Message, UUID> {
    Message getByMessageId(UUID messageId);
}
