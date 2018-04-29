package com.bottle.model.dto.request;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReqRegDTO {
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
