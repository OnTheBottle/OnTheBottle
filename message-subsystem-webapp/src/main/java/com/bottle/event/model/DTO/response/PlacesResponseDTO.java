package com.bottle.event.model.DTO.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PlacesResponseDTO {
    private List<UUID> places;
}
