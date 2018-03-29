package com.bottle.temp.news.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendDTO {
    private UUID id;
    private String name;
    private String pathAvatarImage;
}


