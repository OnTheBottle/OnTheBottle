package com.bottle.event.model.DTO;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EventDTO {
    private String id;
    private String title;
    private String text;
    private String startTime;
    private String endTime;
    private String place;
    private List<UUID> users;
    private String owner;
}
