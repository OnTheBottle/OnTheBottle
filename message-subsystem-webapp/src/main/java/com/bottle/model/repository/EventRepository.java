package com.bottle.model.repository;

import com.bottle.model.entity.Event;
import com.bottle.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findAllByIsActive(boolean isActive, Pageable pageable);

    @Query(value = "select e from Event e join e.users as u where u.id = ?1 and e.isActive = ?2")
    List<Event> getEventsFromUserIsActive(UUID id, boolean isActive, Pageable pageable);

    List<Event> getEventsByOwnerAndIsActiveTrue(User owner, Pageable pageable);

    @Query(value = "select e from Event e where lower(e.title) like lower(concat('%', ?1, '%')) or lower(e.text) like lower(concat('%', ?1, '%'))")
    List<Event> getAllByStringQuery(String query, Pageable pageable);

    @Modifying
    @Query(value = "update Event e set e.isActive = ?2 where e.id = ?1")
    void setEventStatus(UUID id, boolean status);
}