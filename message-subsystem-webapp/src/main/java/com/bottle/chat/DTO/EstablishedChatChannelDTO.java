package com.bottle.chat.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
public class EstablishedChatChannelDTO {
    private UUID channelId;
    private UUID firstUserId;
    private UUID secondUserId;
}