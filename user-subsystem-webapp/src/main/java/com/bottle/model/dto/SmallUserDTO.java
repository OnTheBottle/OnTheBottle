package com.bottle.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmallUserDTO {
    private UUID id;
    private String name;
    private String surname;
    private String avatarUrl;
}
