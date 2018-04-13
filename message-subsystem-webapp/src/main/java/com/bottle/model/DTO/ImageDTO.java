package com.bottle.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ImageDTO {
    private UUID image_id;
    private String name;
    private UUID post_id;
}
