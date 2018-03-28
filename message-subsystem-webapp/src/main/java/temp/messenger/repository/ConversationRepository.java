package temp.messenger.repository;

import temp.messenger.entity.Conversation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ConversationRepository extends CrudRepository<Conversation, UUID> {
    Conversation getByConversationId(UUID conversationId);
}
