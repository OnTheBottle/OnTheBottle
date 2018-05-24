package com.bottle.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class PostDTO {
    private UUID id;
    private String security;
    private UUID user_id;
    private String text;
    private String title;
}
