package com.bottle.news.dao.entity;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "security")
public class Security {

    @Id
//    @GeneratedValue(generator = "INCREMENT")
//    @GenericGenerator(name = "INCREMENT", strategy = "increment")
    @Column(name = "SECURITY_ID", unique = true)
    private int id;

    @Column(name = "NAME")
    private String name;

    public Security() {
    }

    public Security(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Security{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

