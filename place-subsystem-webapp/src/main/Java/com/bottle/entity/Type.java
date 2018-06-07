package com.bottle.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "type")
public class Type {
    @Id
    @Column(name = "type_id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private Set<Place> places = new HashSet<>();
}
