package com.bottle.temp.news.entity;

import javafx.geometry.Pos;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@NoArgsConstructor
@Table(name = "IMAGES")
public class Image {
    private UUID imageId;
    private String path;
    private Set<Post> posts;

    @Id
    @Column(name = "IMAGE_ID")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    public UUID getImageId() {
        return imageId;
    }

    @Column(name = "PATH", nullable = false)
    public String getPath() {
        return path;
    }

    @ManyToMany(mappedBy = "images")
    public Set<Post> getPosts() {
        return posts;
    }
}
