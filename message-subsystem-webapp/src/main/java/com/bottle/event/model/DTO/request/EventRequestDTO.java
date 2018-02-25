package com.bottle.event.model.DTO.request;

import lombok.Data;

@Data
public class EventRequestDTO {
    private String title;
    private String text;
    private String startTime;
    private String endTime;
    private String place;
}
