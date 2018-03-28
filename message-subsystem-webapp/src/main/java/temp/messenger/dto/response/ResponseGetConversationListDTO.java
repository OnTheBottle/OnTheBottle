package temp.messenger.dto.response;

import temp.messenger.entity.Conversation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGetConversationListDTO {
    private List<Conversation> conversations;
}
