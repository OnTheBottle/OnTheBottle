package com.bottle.news.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "security")
public class Security {

    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "UUIDGenerator")
    private byte id;
    private String name;

    public Security(){
    }

    public byte getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
