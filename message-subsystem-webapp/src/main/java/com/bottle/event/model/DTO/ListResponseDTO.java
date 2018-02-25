package com.bottle.event.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ListResponseDTO {
    private List<String> eventList;
}
