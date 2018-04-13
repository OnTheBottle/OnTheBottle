package com.bottle.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class SecurityDTO {
    private UUID security_id;
    private String name;
    private String description;
}