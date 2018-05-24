package com.bottle.chat.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    private String content;
    private UUID senderId;
    private UUID recipientId;
}
