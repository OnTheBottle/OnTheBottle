package com.bottle.userWall.home.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LikeModel {
    private UUID post_id;
    private UUID user_id;
    private String status;
}
