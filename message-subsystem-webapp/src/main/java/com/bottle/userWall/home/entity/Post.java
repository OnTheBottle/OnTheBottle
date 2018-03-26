package com.bottle.userWall.home.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;


@Entity
    @Table(name = "posts")
    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public class Post {
        @Id
        @Column(name = "post_id")
        private UUID id;

        @Column(name = "post", columnDefinition = "TEXT")
        private String Text;

        @Column(name = "title")
        private String title;

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "date")
        @JsonFormat(pattern = "MM-yyyy-dd HH:mm:ss")
        private Date date = new Date();


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "post")
    private Set<Image> images;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "post")
    private Set<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "security_id")
    private Security security;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "post")
    private Set<Like> likes;



    @Override
    public String toString() {
        return "Post{" +
                "id='" + id +
                ", title='" + title + '\'' +
                ", post='" + Text + '\'' +
                ", author='" + user +'\''+
                ", date='" + date +'\''+
                                "'}";
                  }
}