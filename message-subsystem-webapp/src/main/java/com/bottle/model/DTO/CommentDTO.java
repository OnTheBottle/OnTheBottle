package com.bottle.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentDTO {
    private UUID commentId;
    private String comment;
    private UUID postId;
    private UUID userId;
}
