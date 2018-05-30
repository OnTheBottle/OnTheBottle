package com.bottle.chat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class ChatTime {

    public ChatTime(UUID channelId, UUID userId, Date time) {
        this.channelId = channelId;
        this.userId = userId;
        this.time = time;
    }

    public ChatTime(UUID channelId, UUID userId) {
        this.channelId = channelId;
        this.userId = userId;
    }

    @Id
    @NotNull
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    private UUID id;

    @Column
    @NotNull
    private UUID channelId;

    @Column
    @NotNull
    private UUID userId;

    @Column
    private Date time;

}
