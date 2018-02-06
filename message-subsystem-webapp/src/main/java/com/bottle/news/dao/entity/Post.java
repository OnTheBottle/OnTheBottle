package com.bottle.news.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    private String id;
    private String authorId;
    private Date date;
    private String post;
    private Security securityId;

    //@PrimaryKeyJoinColumn
    //@JoinColumn(name = "security_id")
    //@OneToOne(cascade = CascadeType.ALL)

    public Post() {
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "POST_ID")
    public String getId() {
        return id;
    }

    @Column(name = "AUTOR_ID")
    public String getAuthorId() {
        return authorId;
    }

    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    @Column(name = "POST")
    public String getPost() {
        return post;
    }

    //@Column(name = "SECURITY_ID")
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECURITY_ID")
    public Security getSecurityId() {
        return securityId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setSecurityId(Security security) {
        this.securityId = security;
    }
}
