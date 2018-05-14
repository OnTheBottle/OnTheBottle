package com.bottle.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class UsersIdDTO {
    List<UserIdDTO> users;
}
