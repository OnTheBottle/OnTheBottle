package com.bottle.event.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy="place")
    private Set<Event> events = new HashSet<>();
}
