package com.bottle.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"likes","comments","uploadFiles"})
@ToString(exclude = {"likes","comments","uploadFiles"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "post_id")
    private UUID id;

    @Column(name = "post", columnDefinition = "TEXT")
    private String Text;

    @Column(name = "title")
    private String title;

    @Column(name = "deleted")
    private Boolean isDeleted;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    @JsonFormat(pattern = "MM-yyyy-dd HH:mm:ss")
    private Date date = new Date();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "post")
    private Set<UploadFile> uploadFiles;

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

    //@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            //fetch = FetchType.LAZY,
            mappedBy = "post")
    private Set<Like> likes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "FavoriteNews", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "post_id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    private Set<User> favoriteUsers;

/*    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "LikeNews", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "post_id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    private Set<User> likeUsers;
*/
    @ManyToMany(mappedBy = "posts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Saver> savers;
}

