package com.bottle.pubs.places.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
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
    private UUID id;
    @Column(name = "place_name")
    private String name;
    @Column(name = "place_type")
    private String type;
    @Column(name = "place_work_time")
    private String workTime;
    @Column(name = "place_avatar_url")
    private String avatarUrl;
    @Column(name = "commend_id")
    private UUID comment_id;
    @Column(name = "user_id")
    private UUID user_id;
    @Column(name = "comment_time")
    private Instant comment_time;
    @Column(name = "comment_text")
    private String comment_text;

}









