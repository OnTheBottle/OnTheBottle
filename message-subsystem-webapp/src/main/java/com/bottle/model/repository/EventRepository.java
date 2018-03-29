package com.bottle.model.repository;

import com.bottle.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    //@Query("select u from Event u where u.isActive = ?1")
    List<Event> findAllByIsActive(Boolean isActive);
}
