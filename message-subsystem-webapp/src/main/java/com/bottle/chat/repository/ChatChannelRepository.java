package com.bottle.chat.repository;

import com.bottle.chat.entity.ChatChannel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface ChatChannelRepository extends CrudRepository<ChatChannel, UUID> {

    @Query("SELECT c FROM ChatChannel c"
            + "  WHERE c.firstUser.id IN (:firstId, :secondId)"
            + "  AND c.secondUser.id IN (:firstId, :secondId)")
    List<ChatChannel> findExistingChannels(
            @Param("firstId") UUID firstId, @Param("secondId") UUID secondId);

    @Query("SELECT c.id FROM ChatChannel c"
            + "  WHERE c.firstUser.id IN (:firstId, :secondId)"
            + "  AND c.secondUser.id IN (:firstId, :secondId)")
    UUID getChannelId(
            @Param("firstId") UUID firstId, @Param("secondId") UUID secondId);

}