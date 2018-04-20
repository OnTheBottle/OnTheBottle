package com.bottle.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class SecurityDTO {
    private int security_id;
    private String name;
    private String description;
}