package com.bottle.pubs.places.request;

import com.bottle.pubs.places.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentsRequest {
    private List<Comment> commentList;

}
