package com.bottle.model.DTO;

import lombok.Data;

@Data
public class EventRequestDTO {
    private OptionsDTO options;
    private int eventsPage;
    private String sortType;
}
