package com.bottle.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "place_id")
    private UUID id;

    @JsonIgnore
    @OneToMany(mappedBy = "place")
    private Set<Event> events = new HashSet<>();
}
