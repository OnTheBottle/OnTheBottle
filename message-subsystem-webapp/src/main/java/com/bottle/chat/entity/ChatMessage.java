package com.bottle.chat.entity;

import com.bottle.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "chatMessage")
public class ChatMessage {

    public ChatMessage(User author, User recipient, String content) {
        this.author = author;
        this.recipient = recipient;
        this.time = new Date();
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID messageId;

    @OneToOne
    @JoinColumn(name = "authorId")
    private User author;

    @OneToOne
    @JoinColumn(name = "recipientId")
    private User recipient;

    @NotNull
    private Date time;

    private String content;
}