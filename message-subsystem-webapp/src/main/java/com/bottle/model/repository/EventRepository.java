package com.bottle.model.repository;

import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    Set<Event> findAllByIsActive(boolean isActive);

    @Query(value = "SELECT * FROM users INNER JOIN event_users ON users.user_id = event_users.user_id INNER JOIN event ON event_users.event_id = event.event_id where users.user_id = ?1 AND event.is_active = ?2",
            nativeQuery=true)
    Set<Event> getEventsFromUserIsActive(UUID id, boolean isActive);

    Set<Event> getEventsByOwnerAndIsActiveTrue(User owner);
}
