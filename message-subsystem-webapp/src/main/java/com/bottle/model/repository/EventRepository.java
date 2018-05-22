package com.bottle.model.repository;

import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findAllByIsActive(boolean isActive, Pageable pageable);

    @Query(value = "SELECT e FROM Event e join e.users as u where u.id = ?1 and e.isActive = ?2")
    List<Event> getEventsFromUserIsActive(UUID id, boolean isActive, Pageable pageable);

    List<Event> getEventsByOwnerAndIsActiveTrue(User owner, Pageable pageable);
}