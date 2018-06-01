package com.bottle.model.DTO.Request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EventDTO {
    private UUID id;
    private String title;
    private String text;
    private String startTime;
    private String endTime;
    private UUID place;
    private List<UUID> users;
    private UUID owner;
    private boolean addPost;
}
