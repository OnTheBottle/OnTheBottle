package com.bottle.chat.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
public class ReadingTimeDTO {
    private String token;
    private UUID channelId;
    private Date time;

    public ReadingTimeDTO(String token, UUID channelId, long time) {
        this.token = token;
        this.channelId = channelId;
        this.time = new Date(time);
    }
}
