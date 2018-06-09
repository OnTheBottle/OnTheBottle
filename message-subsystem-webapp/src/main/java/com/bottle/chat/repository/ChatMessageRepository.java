package com.bottle.chat.repository;

import com.bottle.chat.entity.ChatMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage, UUID> {

    @Query("SELECT m FROM ChatMessage m"
            + "  WHERE m.sender.id IN (:userIdOne, :userIdTwo)"
            + "  AND m.recipient.id IN (:userIdOne, :userIdTwo)"
            + "  ORDER BY m.time DESC")
    List<ChatMessage> getExistingChatMessages(
            @Param("userIdOne") UUID userIdOne, @Param("userIdTwo") UUID userIdTwo, Pageable pageable);

    //    @Query("SELECT COUNT(m) FROM ChatMessage m " +
//            "WHERE m.time=:time AND m.recipient.id=:authId")
//    int getNumberOfUnreadMessages(
//            @Param("authId") UUID authId,
//            @Param("time") Date time);
//
    @Query("SELECT COUNT(m) FROM ChatMessage m " +
            "WHERE m.time=:time AND m.sender.id=:interlocutorId AND m.recipient.id=:authId")
    int getNumberOfUnreadMessages(
            @Param("authId") UUID authId,
            @Param("interlocutorId") UUID interlocutorId,
            @Param("time") Date time);
}
