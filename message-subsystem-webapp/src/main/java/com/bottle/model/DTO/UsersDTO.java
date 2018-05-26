package com.bottle.model.DTO;

import com.bottle.model.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UsersDTO {
    private List<User> friends;
    private List<User> users;
}
