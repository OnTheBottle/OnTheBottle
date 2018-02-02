package com.bottle.event.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
public class Event {

    @Id @GeneratedValue
    private long id;

    @Column(name = "Text", nullable = false)
    private String text;

    @Column(name = "Start_time", nullable = false)
    private Calendar startTime;

    @Column(name = "End_time", nullable = false)
    private Calendar endTime;
}
