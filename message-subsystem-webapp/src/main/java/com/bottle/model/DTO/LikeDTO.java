package com.bottle.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class LikeDTO {
    private UUID like_id;
    private UUID post_id;
    private UUID user_id;
    private String status;
}
