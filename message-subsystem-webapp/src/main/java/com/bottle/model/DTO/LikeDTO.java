package com.bottle.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class LikeDTO {
    private UUID likeId;
    private UUID postId;
    private UUID userId;
    private String status;
}
