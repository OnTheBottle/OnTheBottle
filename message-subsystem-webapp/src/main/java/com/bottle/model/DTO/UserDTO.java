package com.bottle.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDTO {
    private UUID user_id;
    private String name;
    private String surname;
    private String avatarUrl;
}

