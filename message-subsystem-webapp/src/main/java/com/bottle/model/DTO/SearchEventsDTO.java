package com.bottle.model.DTO;

import lombok.Data;

@Data
public class SearchEventsDTO {
    private String searchQuery;
    private int eventsPage;
}
