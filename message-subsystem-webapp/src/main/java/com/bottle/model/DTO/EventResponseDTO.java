package com.bottle.model.DTO;

import com.bottle.model.entity.Place;
import com.bottle.model.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class EventResponseDTO {
    private UUID id;
    private String title;
    private String text;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date endTime;
    private Place place; //TODO UUID
    private boolean member;
    private boolean active;
    private boolean owner;
    private int usersCounter;
    private int friendsCounter;
    private List<User> users;
    private List<User> friends;
}
