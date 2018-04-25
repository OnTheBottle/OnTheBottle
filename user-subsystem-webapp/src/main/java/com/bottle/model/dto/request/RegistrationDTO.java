package com.bottle.model.dto.request;

import lombok.*;

@Getter
@ToString
public class RegistrationDTO {
    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private int age;
    private String country;
    private String city;
    private String avatarUrl;
}
