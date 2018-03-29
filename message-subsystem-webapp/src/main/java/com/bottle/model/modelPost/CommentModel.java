package com.bottle.model.modelPost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CommentModel {

    private String comment;
    private String post_id;
    private String user_id;

}
