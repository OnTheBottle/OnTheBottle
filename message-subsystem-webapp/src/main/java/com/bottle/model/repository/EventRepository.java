package com.bottle.model.repository;

import com.bottle.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    Set<Event> findAllByIsActive(Boolean isActive);

    @Query(value = "SELECT * FROM users INNER JOIN event_users ON users.user_id = event_users.user_id INNER JOIN event ON event_users.event_id = event.event_id where users.user_id = ?0 AND event.is_active = ?1",
            nativeQuery=true
    )
    Set<Event> getEventsFromUserIsActive(UUID id, boolean isActive);
}
