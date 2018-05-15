package com.bottle.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"posts"})
@ToString(exclude = {"posts"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "saver")
public class Saver {
    @Id
    @Column(name = "saver_id")
    private UUID id;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Posts_Saver",
            joinColumns = {@JoinColumn(name = "saver_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id")}
    )
    private Set<Post> posts;


}
