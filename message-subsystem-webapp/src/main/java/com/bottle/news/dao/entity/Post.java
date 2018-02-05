package com.bottle.news.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementID")
    @GenericGenerator(name = "incrementID", strategy = "increment")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "autor_id")
    private long authorId;

    @Column(name = "date")
    private Date date;

    @Column(name = "text")
    private String text;

    @Column(name = "security_id")
    private byte securityId;

    public Post() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte getSecurityId() {
        return securityId;
    }

    public void setSecurityId(byte security) {
        this.securityId = security;
    }
}
