package com.bottle.model.dto.response;

import com.bottle.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// TODO: 24.04.2018 I think you understand what is wrong
public class InfoPageResponse {
    private String name;
    private String surname;
    private int age;
    private String email;
    private String country;
    private String city;
    private String avatarUrl;
}
