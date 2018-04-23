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
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "place_id")
    private UUID placeid;

    @Column(name = "place_name")
    private String name;

    @Column(name = "place_type")
    private String type;

    @Column(name = "place_work_time")
    private String workTime;

    @Column(name = "place_avatar_url")
    private String avatarUrl;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "place")
    private List<Comment> comments;
}









