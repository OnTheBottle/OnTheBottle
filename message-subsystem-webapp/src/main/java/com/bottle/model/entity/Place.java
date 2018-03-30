package com.bottle.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "place_id")
    private UUID id;

    @OneToMany(mappedBy = "place")
    private Set<Event> events = new HashSet<>();
}
