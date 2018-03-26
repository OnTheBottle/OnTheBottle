package com.bottle.pubs.list.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "place")
public class Place {
    private UUID id;
    private String place_name;
    private String place_type;
    private String place_work_time;
    private String place_avatar_url;

    public Place() {
    }

    public Place(UUID id, String place_name, String place_type, String place_work_time, String place_avatar_url) {
        this.id = id;
        this.place_name = place_name;
        this.place_type = place_type;
        this.place_work_time = place_work_time;
        this.place_avatar_url = place_avatar_url;
    }

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name="place_name")
    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    @Column(name = "place_type")
    public String getPlace_type() {
        return place_type;
    }

    public void setPlace_type(String place_type) {
        this.place_type = place_type;
    }

    @Column (name = "place_work_time")
    public String getPlace_work_time() {
        return place_work_time;
    }

    public void setPlace_work_time(String place_work_time) {
        this.place_work_time = place_work_time;
    }

    @Column (name = "place_avatar_url")
    public String getPlace_avatar_url() {
        return place_avatar_url;
    }

    public void setPlace_avatar_url(String place_avatar_url) {
        this.place_avatar_url = place_avatar_url;
    }
}






