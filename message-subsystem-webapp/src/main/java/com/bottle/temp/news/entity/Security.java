package com.bottle.temp.news.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Setter
@NoArgsConstructor
@Table(name = "SECURITY")
public class Security {
    private Integer id;
    private String name;
    private String description;

    @Id
    @Column(name = "SECURITY_ID", unique = true)
    public Integer getId() {
        return id;
    }

    @Column(name = "NAME", unique = true)
    public String getName() {
        return name;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }
}
