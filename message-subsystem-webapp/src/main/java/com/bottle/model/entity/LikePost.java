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
@Table(name = "LIKE_POSTS")
public class LikePost {
    private UUID likeId;
    private UUID postId;
    private UUID userId;
    private Date date;

    @Id
    @Column(name = "LIKE_ID")
    public UUID getLikeId() {
        return likeId;
    }

    @Column(name = "POST_ID")
    public UUID getPostId() {
        return postId;
    }

    @Column(name = "USER_ID")
    public UUID getUserId() {
        return userId;
    }

    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "LikePost{" +
                "likeId=" + likeId +
                ", postId=" + postId +
                ", userId=" + userId +
                ", date=" + date +
                '}';
    }
}
