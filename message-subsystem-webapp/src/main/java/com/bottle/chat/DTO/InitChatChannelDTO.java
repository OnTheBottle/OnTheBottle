package com.bottle.chat.DTO;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class InitChatChannelDTO {
    private String token;
    private UUID senderId;
    private UUID recipientId;
}
