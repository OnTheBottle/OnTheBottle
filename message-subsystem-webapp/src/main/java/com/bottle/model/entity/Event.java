package com.bottle.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
public class Event {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "event_id")
    private UUID id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Text")
    private String text;

    @Column(name = "Start_time")
    private Date startTime;

    @Column(name = "End_time")
    private Date endTime;

    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "isActive")
    private Boolean isActive;
}
