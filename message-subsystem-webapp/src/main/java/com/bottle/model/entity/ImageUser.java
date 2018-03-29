package com.bottle.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="imageusers")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ImageUser implements Serializable {
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "imageuser_id")
    private UUID id;

    @Column(name = "path")
    private String path;

    @JsonIgnore
    @OneToOne( mappedBy = "imageUser",fetch = FetchType.LAZY)
     private User user;

    public ImageUser() {
    }

    public ImageUser(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ImageUser{" +
                "path='" + path + '\'' +
                '}';
    }
}
