package com.bottle.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private UUID user_id;
    private String name;
    private String surname;
    private String avatarUrl;
}

