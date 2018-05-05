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

public class InfoPageResponse {
    private String name;
    private String surname;
    private int age;
    private String email;
    private String country;
    private String city;
    private String avatarUrl;
    private String status;
}
