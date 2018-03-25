package com.bottle.event.model.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class EventDTO {
    private String id;

    @NotNull
    private String title;

    private String text;

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;

    @NotNull
    private String place;

    private List<UUID> users;

    @NotNull
    private String owner;
}
