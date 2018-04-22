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
@Table(name = "comment")
public class Comment {
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "commend_id")
    private UUID comment_id;
    @Column(name = "user_id")
    private UUID user_id;
    @Column(name = "comment_time")
    private Instant comment_time;
    @Column(name = "comment_text")
    private String comment_text;
    @Column(name = "place_id")
    private UUID place_id;

}









