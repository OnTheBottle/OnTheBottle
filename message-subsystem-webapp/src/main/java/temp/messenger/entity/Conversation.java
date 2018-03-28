package temp.messenger.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "conversation")
@Getter
@Setter
@NoArgsConstructor
public class Conversation {
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "conversation_id")
    private UUID conversationId;
    @Column(name = "title")
    private String title;
    @Column(name = "last_message")
    private UUID lastMessageId;

    // TODO: 22.02.2018 read about Pageable 
    @OneToMany
    @JoinTable(name="message_to_conversation", joinColumns = @JoinColumn(name = "message_id"), inverseJoinColumns = @JoinColumn(name = "conversation_id"))
    private Set<Message> messages;


}