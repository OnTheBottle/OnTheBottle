package com.bottle.temp.news.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@NoArgsConstructor
@Table(name = "POSTS")
public class Post {
    private UUID id;
    private UUID authorId;
    private Date date;
    private String title;
    private String post;
    //private Security securityId;
    private Set<Image> images;
    private Set<Comment> comments;
    private Set<FavoritePost> favorites;


    @Id
    @Column(name = "POST_ID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @GeneratedValue(generator = "UUID")
    public UUID getId() {
        return id;
    }

    @Column(name = "AUTHOR_ID")
    public UUID getAuthorId() {
        return authorId;
    }

    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    @Column(name = "POST", columnDefinition = "TEXT")
    public String getPost() {
        return post;
    }

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "SECURITY_ID")
//    public Security getSecurityId() {
//        return securityId;
//    }


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "POSTS_IMAGES",
            joinColumns = {@JoinColumn(name = "POST_ID")},
            inverseJoinColumns = {@JoinColumn(name = "IMAGE_ID")}
    )
    public Set<Image> getImages() {
        return images;
    }

    //@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @OneToMany
    @JoinColumn(name = "POST_ID")
    public Set<Comment> getComments() {
        return comments;
    }

    @OneToMany
    @JoinColumn(name = "POST_ID")
    public Set<FavoritePost> getFavorites() {
        return favorites;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", post='" + post + '\'' +
                '}';
    }
}
