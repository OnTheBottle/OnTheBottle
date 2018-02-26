package com.bottle.event.model.DTO;

import lombok.Data;

@Data
public class EventDTO {
    private String title;
    private String text;
    private String startTime;
    private String endTime;
    private String place;
}
