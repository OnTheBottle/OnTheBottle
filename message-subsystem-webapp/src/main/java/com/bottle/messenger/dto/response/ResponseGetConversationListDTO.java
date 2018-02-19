package com.bottle.messenger.dto.response;

import com.bottle.messenger.entity.Conversation;
import com.bottle.messenger.entity.Message;
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
