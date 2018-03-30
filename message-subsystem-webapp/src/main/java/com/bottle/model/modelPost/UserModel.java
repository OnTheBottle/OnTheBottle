package com.bottle.model.modelPost;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
public class UserModel {
        private UUID user_id;
        private String name;
        private String s;
}

