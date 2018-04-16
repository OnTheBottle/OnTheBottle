package com.bottle.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"posts"})
@ToString(exclude = {"posts"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "securites")
public class Security {
    @Id
    @Column(name = "security_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "security")
    private Set<Post> posts;
}


