package com.bottle.event.model.DTO.response;

import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class EventsResponseDTO {
    private Map<UUID, String> activeEvents;
    private Map<UUID, String> passedEvents;
}
