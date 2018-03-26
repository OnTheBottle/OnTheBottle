package com.bottle.news.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String nickName;
    private String pathAvatarImage;

    public User() {
    }

    public User(String id, String firstName, String lastName, String nickName, String pathAvatarImage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.pathAvatarImage = pathAvatarImage;
    }

    @Id
    @Column(name = "USER_ID", unique = true, nullable = false, updatable = false)
    public String getId() {
        return id;
    }
    @Column(name = "FIRSTNAME", nullable = false)
    public String getFirstName() {
        return firstName;
    }
    @Column(name = "LASTNAME", nullable = false)
    public String getLastName() {
        return lastName;
    }
    @Column(name = "NICKNAME", nullable = false)
    public String getNickName() {
        return nickName;
    }
    @Column(name = "PATH_AVATAR")
    public String getPathAvatarImage() {
        return pathAvatarImage;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPathAvatarImage(String pathAvatarImage) {
        this.pathAvatarImage = pathAvatarImage;
    }


        @Override
        public String toString () {
            return "User{" +
                    "id='" + id + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", pathAvatarImage='" + pathAvatarImage + '\'' +
                    '}';
        }
    }
