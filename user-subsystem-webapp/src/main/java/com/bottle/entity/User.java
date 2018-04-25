package com.bottle.entity;

import com.bottle.model.dto.request.RegistrationRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user", schema = "public")
public class User {
    // TODO: 24.04.2018 plz use lombok....very terrible part
    @Id
    @GeneratedValue(generator = "uuid-gen")
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @Column(name = "id")
    private UUID id;
    @Column(name = "age")
    private int age;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "avatar_url")
    private String avatarUrl;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
    private Boolean deleted = false;

    //@ManyToMany(cascade = CascadeType.ALL)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "RelationShip", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> friends;

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User(RegistrationRequest request) {
        this.login = request.getLogin();
        this.password = request.getPassword();
        this.email = request.getEmail();
        this.age = request.getAge();
        this.name = request.getName();
        this.surname = request.getSurname();
        this.avatarUrl = request.getAvatarUrl();
        this.country = request.getCountry();
        this.city = request.getCity();
    }
}
