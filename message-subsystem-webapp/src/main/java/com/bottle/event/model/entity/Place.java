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
public class Place {
    @Id
    @Column(columnDefinition = "BINARY(16)", name = "place_id")
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name="Event_Places",
            joinColumns={@JoinColumn(name="place_id")},
            inverseJoinColumns={@JoinColumn(name="event_id")}
    )
    private Set<Event> events = new HashSet<>();
}
