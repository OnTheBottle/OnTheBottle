package com.bottle.chat.DTO;


import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.UUID;

@ToString
@Getter
public class ChatNotificationDTO {
    private String notificationType;
    private Integer newMessageCounter;
    private Map<UUID, Integer> newMessageCounterArray;

    public ChatNotificationDTO(Integer newMessageCounter, Map<UUID, Integer> newMessageCounterArray) {
        this.notificationType = "chat";
        this.newMessageCounter = newMessageCounter;
        this.newMessageCounterArray = newMessageCounterArray;
    }
}
