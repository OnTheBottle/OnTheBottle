package com.bottle.model.DTO.request;

import lombok.Data;

import java.util.UUID;

@Data
public class IdEventAndUserRequestDTO {
    private UUID idEvent;
    private UUID idUser;
}