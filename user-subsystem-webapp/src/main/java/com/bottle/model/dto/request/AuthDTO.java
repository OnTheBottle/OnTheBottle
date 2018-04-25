package com.bottle.model.dto.request;

import lombok.*;

@Getter
@ToString
public class AuthDTO {
    private String login;
    private String password;
}
