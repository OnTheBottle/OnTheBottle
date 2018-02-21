package com.bottle.event.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(exclude = {"users", "places"})
@ToString(exclude = {"users", "places"})
public class Event {

    @Id @GeneratedValue
    @Column(columnDefinition = "BINARY(16)", name = "event_id")
    private UUID id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Text")
    private String text;

    @Column(name = "Start_time", nullable = false)
    private Instant startTime;

    @Column(name = "End_time", nullable = false)
    private Instant endTime;

    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY)
    private Set<Place> places = new HashSet<>();
}
