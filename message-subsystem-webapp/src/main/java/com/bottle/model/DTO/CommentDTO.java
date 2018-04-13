package com.bottle.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentDTO {
    private UUID comment_id;
    private String comment;
    private UUID post_id;
    private UUID user_id;

}
