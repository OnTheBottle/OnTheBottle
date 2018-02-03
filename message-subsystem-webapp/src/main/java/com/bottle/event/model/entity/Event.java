package com.bottle.event.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
public class Event {

    @Id @GeneratedValue
    private long id;

    @Column(name = "Text")
    private String text;

    @Column(name = "Start_time", nullable = false)
    private Date startTime;

    @Column(name = "End_time", nullable = false)
    private Date endTime;

    @Column(name = "Place", nullable = false)
    private int idPlace;
}
