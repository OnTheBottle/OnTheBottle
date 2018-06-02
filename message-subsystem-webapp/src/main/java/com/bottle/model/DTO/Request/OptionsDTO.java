package com.bottle.model.DTO.Request;

import lombok.Data;

@Data
public class OptionsDTO {
    private boolean allEvents;
    private boolean activeEvents;
    private boolean ownerEvents;
}
