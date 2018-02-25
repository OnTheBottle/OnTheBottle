package com.bottle.event.model.DTO.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EventsResponseDTO {
    private List<UUID> eventsId;
    private List<String> eventTitle;
}
