package com.bottle.model.DTO;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserEventDTO {
    private UUID eventId;
    private UUID userId;
}
