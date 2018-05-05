package com.bottle.model.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Setter
@Table(name = "FAVORITE_POSTS")
public class FavoriteNews {
    private UUID favoriteId;
    private UUID postId;
    private UUID userId;
    private Date date;

    @Id
    @Column(name = "FAVORITE_ID")
    public UUID getFavoriteId() {
        return favoriteId;
    }

    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    @Column(name = "USER_ID")
    public UUID getUserId() {
        return userId;
    }

    @Column(name = "POST_ID")
    public UUID getPostId() {
        return postId;
    }

    @Override
    public String toString() {
        return "FavoriteNews{" +
                "favoriteId=" + favoriteId +
                ", postId=" + postId +
                ", userId=" + userId +
                ", date=" + date +
                '}';
    }
}

