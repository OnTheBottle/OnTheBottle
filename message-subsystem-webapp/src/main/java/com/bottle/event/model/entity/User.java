package com.bottle.event.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(exclude = "events")
@ToString(exclude = "events")
public class User {
    @Id
    @Column(columnDefinition = "BINARY(16)", name = "user_id")
    private UUID id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name="Event_Users",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="event_id")}
    )
    private Set<Event> events = new HashSet<>();

    public void addUserToAllEvents() {
        events.forEach(event -> event.getUsers().add(this));
    }
}
