package com.bottle.pubs.places.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "place")
public class Place {

    // TODO: 24.04.2018 CAMEL CASE NOTATION !!!!

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "placeId")
    private UUID placeId;

    @Column(name = "placeName")
    private String name;

    @Column(name = "placeType")
    private String type;

    @Column(name = "placeWorkTime")
    private String workTime;

    @Column(name = "placeAvatarUrl")
    private String avatarUrl;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "place")
    private List<Comment> comments;
}









