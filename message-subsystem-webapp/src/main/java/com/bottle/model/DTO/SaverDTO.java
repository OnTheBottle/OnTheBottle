package com.bottle.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class SaverDTO {
    private UUID saverId;
    private UUID postId;
}
