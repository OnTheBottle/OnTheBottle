package com.bottle.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class PostDTO {
    private UUID post_id;
    private int security_id;
    private UUID user_id;
    private String text;
    private String title;
}
