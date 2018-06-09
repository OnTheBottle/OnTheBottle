package com.bottle.model.DTO.Request;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class EventDTO {
    private UUID id;
    private String title;
    private String text;
    private Date startTime;
    private Date endTime;
    private UUID place;
    private List<UUID> users;
    private UUID owner;
}
