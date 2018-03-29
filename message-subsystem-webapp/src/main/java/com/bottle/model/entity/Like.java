package com.bottle.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "likes")
public class Like {
    @Id
    @GenericGenerator( name="uuid-gen", strategy="uuid2" )
    @GeneratedValue(generator="uuid-gen")
    @Column(name = "like_id")
    private UUID like_id;

    @Column(name="status")
    private String status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Like{" +
                "like_id=" + like_id +
                ", status='" + status + '\'' +
                ", post=" + post +
                ", user=" + user +
                '}';
    }
}
