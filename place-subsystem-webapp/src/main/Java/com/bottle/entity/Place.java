package com.bottle.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "place")
public class Place {
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "place_id")
    private UUID placeId;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "image")
    private String image;
}









