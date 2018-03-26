package com.bottle.messenger.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "message_id")
    private UUID messageId;
    @Column(name = "author_id")
    private UUID authorId;
    @Column(name = "create_date")
    private Instant createDate;
    @Column(name = "update_date")
    private Instant updateDate;
    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinTable(name="message_to_conversation", joinColumns = @JoinColumn(name = "conversation_id"), inverseJoinColumns = @JoinColumn(name = "message_id"))
    private Conversation conversation;
}