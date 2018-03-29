package com.bottle.temp.news.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@NoArgsConstructor
@Table(name = "COMMENTS")
public class Comment {

    private UUID commentId;
    private UUID authorId;
    private Date date;
    private String comment;
    private Post post;

    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    public UUID getCommentId() {
        return commentId;
    }

    @Column(name = "AUTHOR_ID")
    public UUID getAuthorId() {
        return authorId;
    }

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    public Post getPost() {
        return post;
    }

    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    @Column(name = "COMMENT", columnDefinition = "TEXT")
    public String getComment() {
        return comment;
    }
}
