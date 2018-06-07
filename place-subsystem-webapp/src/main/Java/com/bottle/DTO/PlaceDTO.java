package com.bottle.DTO;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PlaceDTO {
    private UUID id;
    private String title;
    private String text;
    private Date startTime;
    private Date endTime;
    private UUID type;
    private String image;
}
