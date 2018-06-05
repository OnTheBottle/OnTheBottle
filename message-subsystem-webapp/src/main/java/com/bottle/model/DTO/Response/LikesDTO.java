package com.bottle.model.DTO.Response;

import com.bottle.model.entity.Like;
import lombok.Data;

import java.util.List;

@Data
public class LikesDTO {
    private int countLike;
    private int countDislike;
    private boolean UserLike;
    private boolean UserDislike;
    List<Like> likes;
}
