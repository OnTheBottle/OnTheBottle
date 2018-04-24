package com.bottle.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestEventDTO {
    UUID userId;
    OptionsDTO options;
}
