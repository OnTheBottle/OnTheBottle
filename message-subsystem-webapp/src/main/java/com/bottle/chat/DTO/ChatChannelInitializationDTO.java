package com.bottle.chat.DTO;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class ChatChannelInitializationDTO {
    private UUID userIdOne;
    private UUID userIdTwo;
}
