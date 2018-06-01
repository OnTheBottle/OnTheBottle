package com.bottle.model.DTO.Request;

import lombok.Data;

@Data
public class SearchEventsDTO {
    private String searchQuery;
    private int eventsPage;
}
