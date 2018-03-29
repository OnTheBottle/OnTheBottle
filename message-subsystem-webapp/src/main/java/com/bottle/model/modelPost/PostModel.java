package com.bottle.model.modelPost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostModel {
    private String post_id;
    private String post;
    private String security_id;
    private String title;
    private String user_id;

}
