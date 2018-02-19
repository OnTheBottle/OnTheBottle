package com.bottle.messenger.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "person_to_conversation")
@Getter
@Setter
@NoArgsConstructor
public class PersonToConversation {

    @Column(name = "person_id")
    private UUID personId;
    @Column(name = "conversation_id")
    private UUID conversationId;
}
