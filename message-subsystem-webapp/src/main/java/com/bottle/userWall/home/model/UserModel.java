package com.bottle.userWall.home.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserModel {
        private UUID user_id;
        private String name;
        private String status;
}

