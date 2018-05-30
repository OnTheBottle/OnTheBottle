package com.bottle.chat.repository;

import com.bottle.chat.entity.ChatTime;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Transactional
@Repository
public interface ChatTimeRepository extends CrudRepository<ChatTime, UUID> {

    @Modifying
    @Query("UPDATE ChatTime t SET t.time = :time WHERE t.channelId = :channelId AND t.userId = :userId")
    void setReadingTime(@Param("userId") UUID userId, @Param("channelId") UUID channelId, @Param("time") Date time);

    @Query("SELECT t.time FROM ChatTime t WHERE t.channelId=:channelId AND t.userId=:userId")
    Date getReadingTime(@Param("userId") UUID userId, @Param("channelId") UUID channelId);
}
