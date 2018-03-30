package com.bottle.model.modelPost;

import lombok.*;

@Data
public class CommentModel {
    private String comment;
    private String post_id;
    private String user_id;
}
