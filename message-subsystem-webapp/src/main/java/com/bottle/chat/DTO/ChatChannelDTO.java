package com.bottle.chat.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Map;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
public class ChatChannelDTO {
    private UUID channelId;
    private Map firstUser;
    private Map secondUser;
}