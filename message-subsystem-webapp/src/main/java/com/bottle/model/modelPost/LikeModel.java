package com.bottle.model.modelPost;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
public class LikeModel {
    private UUID post_id;
    private UUID user_id;
    private String status;
}
