package com.bottle.event.model.repository;

import com.bottle.event.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("select u from Event u where u.id = ?1 and u.isDelete = ?2 ")
    Event findByIsDelete(UUID id, Boolean isDelete);

    @Query("select u from Event u where u.isDelete = ?1")
    List<Event> findAllByIsDelete(Boolean isDelete);
}
